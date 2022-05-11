package controller;

import collection.CollectionItem;
import collection.CollectionManager;
import collection.LinkedListCollectionManager;
import collection.StringTable;
import collection.exceptions.CollectionException;
import collection.exceptions.InvalidInputException;
import controller.actions.*;
import controller.actions.basic.CommandInputAction;
import controller.commands.*;
import controller.commands.basic.*;
import controller.commands.exceptions.*;
import controller.exceptions.ClosingAppException;
import controller.input.*;
import controller.input.exception.EndOfScriptFileException;
import interaction.requests.*;
import interaction.ui.*;

import javax.script.ScriptException;
import java.io.FileNotFoundException;
import java.util.*;

public class ApplicationController<T extends CollectionItem>
        implements Controller, CommandManager
{
    private final InterfaceComponent view;
    private final CollectionManager<T> collectionManager;

    private final Map<String, Command> commandMap;
    private final ActionManager actionManager;
    private final InputManager inputManager;
    private ArrayDeque<String> history = new ArrayDeque<String>();

    private boolean consoleMode = true;

    {
        commandMap = new LinkedHashMap<>();
        actionManager = new ConcreteActionManager();

    }

    public ApplicationController(InterfaceComponent view, CollectionManager<T> collectionManager) {
        this.view = view;
        this.collectionManager = collectionManager;

        inputManager = new InputManager(new ConsoleInputStrategy(view));

        addCommand(new HelpCommand(this, view));
        addCommand(new InfoCommand(this, view, collectionManager));
        addCommand(new ShowCommand(this, view, collectionManager));
        addCommand(new AddCommand<>(this, collectionManager));
        addCommand(new ClearCommand(this, view, collectionManager));
        addCommand(new ExitCommand(this, view));
        addCommand(new SaveCommand(this, collectionManager));
        addCommand(new UpdateCommand<>(this, collectionManager));
        addCommand(new ExecuteScriptCommand(this, view));
        addCommand(new RemoveByID(this, collectionManager));
        addCommand(new RemoveHeadCommand(this, view, (LinkedListCollectionManager<?>) collectionManager));
        addCommand(new HistoryCommand(this, view, history));
        addCommand(new PrintAscendingCommand(this, view, collectionManager));
    }


    @Override
    public void addCommand(Command command) {
        commandMap.put(command.getName(), command);
    }

    @Override
    public boolean removeCommand(String commandName) {
        if(!hasCommand(commandName)) return false;
        commandMap.remove(commandName);
        return true;
    }

    @Override
    public boolean hasCommand(String commandName) {
        return commandMap.keySet().stream().anyMatch(name -> name.equals(commandName));
    }

    @Override
    public void executeCommand(String commandName, CommandParameters params) {
        if(!hasCommand(commandName)) throw new NoSuchCommandException(commandName);
        commandMap.get(commandName).execute(params);
        history.add(commandName);
        if(history.size()>10)
            history.poll();
    }

    private void toggleMode() {
        if(ScriptInputStrategy.stackSize()==0) { consoleMode(); actionManager.clear(); }
        else inputManager.setStrategy(ScriptInputStrategy.pop());
        actionManager.clear();
    }

    public void consoleMode() {
        if(!consoleMode) actionManager.clear();;
        consoleMode = true;
        inputManager.setStrategy(new ConsoleInputStrategy(view));
    }

    @Override
    public void fileMode(String absPath) {
        try {
            inputManager.setStrategy(new ScriptInputStrategy(absPath));
            actionManager.clear();
            consoleMode = false;
        } catch (FileNotFoundException e) {
            displayError(e.getMessage());
        }
    }

    public void close() {
        throw new ClosingAppException("Closing app...");
    }

    @Override
    public void displayError(String errorMsg) {
        if(consoleMode) view.handleRequest(new InterfaceRequest(InterfaceRequestType.ERROR, errorMsg));
    }

    @Override
    public void exec() {
        while(true) {
            if(actionManager.size()==0) {
                actionManager.pushAction(new CommandInputAction(inputManager, this));
            }
            else {
                try {
                    actionManager.act();
                    actionManager.poll();
                } catch (ScriptOverloadException e) {
                    consoleMode();
                    displayError(e.getMessage());
                } catch (OpenFileException e) {
                    actionManager.clear();
                    displayError(e.getMessage());
                } catch (CollectionException | InvalidInputException e) {
                    if(!consoleMode) consoleMode();
                    displayError(e.getMessage());
                } catch (EndOfScriptFileException e) {
                    toggleMode();
                } catch (ClosingAppException e) {
                    displayError(e.getMessage());
                    break;
                } catch (CommandException e) {
                    actionManager.poll();
                    if(!consoleMode) consoleMode();
                    displayError(e.getMessage());
                }
            }
        }
    }

    public String path;
    @Override
    public String savePath() {
        return path;
    }

    @Override
    public void setSavePath(String savePath) {
        path = savePath;
    }

    public InterfaceComponent getView() {
        return view;
    }

    public CollectionManager<?> getCollectionManager() {
        return collectionManager;
    }

    public ActionManager getActionManager() {
        return actionManager;
    }

    public InputManager getInputManager() {
        return inputManager;
    }

    @Override
    public CommandManager getCommandManager() {
        return this;
    }

    @Override
    public StringTable getInfoTable() {
        return new InfoTable();
    }

    public class InfoTable implements StringTable {
        @Override
        public List<String> getTitles() {
            return new ArrayList<>(Arrays.asList(
                    "name",
                    "description"
            ));
        }

        @Override
        public List<Map<String, String>> getTable() {
            return commandMap.keySet().stream().collect(
                    ArrayList::new,
                    (list, item) -> {
                        Command command = commandMap.get(item);
                        list.add(StringTable.stringMatrixToMap(new String[][]{
                                {"name", command.getName()},
                                {"description", command.getDescription()}
                        }));
                    },
                    ArrayList::addAll
            );
        }
    }
}
