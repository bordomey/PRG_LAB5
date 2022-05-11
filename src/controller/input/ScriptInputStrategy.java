package controller.input;

import controller.commands.exceptions.CommandException;
import controller.commands.exceptions.OpenFileException;
import controller.commands.exceptions.ScriptOverloadException;
import controller.input.exception.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;
import java.util.Stack;

public class ScriptInputStrategy implements InputStrategy {
    public final File file;
    public Scanner scanner;
    public String absPath;

    static private final Stack<ScriptInputStrategy> scriptStack = new Stack<>();

    static private void push(ScriptInputStrategy inputStrategy) {
        if(scriptStack.size()>0&&scriptStack.stream().anyMatch(iter -> iter.getAbsPath().equals(inputStrategy.getAbsPath()))) throw new ScriptOverloadException();
        scriptStack.push(inputStrategy);
    }

    public String getAbsPath() {
        return file.getAbsolutePath();
    }

    public ScriptInputStrategy(String absPath) throws CommandException, FileNotFoundException {
        this.file = new File(absPath);
        this.absPath = absPath;
        if(!file.canRead()) throw new OpenFileException(absPath, "Denied read");
        this.scanner = new Scanner(file);

        push(this);
    }

    static public long stackSize() {
        return scriptStack.size();
    }

    static public ScriptInputStrategy pop() {
        return scriptStack.pop();
    }

    @Override
    public void handleRequest(InputRequest inputAction) {
        if(!scanner.hasNextLine()) {
            if(scriptStack.size()!=0) scriptStack.pop();
            throw new EndOfScriptFileException();
        };
        inputAction.respond(scanner.nextLine());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScriptInputStrategy that = (ScriptInputStrategy) o;
        return Objects.equals(file, that.file);
    }

    @Override
    public int hashCode() {
        return Objects.hash(file);
    }
}
