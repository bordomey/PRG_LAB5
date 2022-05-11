package controller.commands;


import controller.commands.exceptions.NoSuchCommandParamException;

import java.util.*;

public class CommandParameters {
    private final List<String> params;

    public CommandParameters(String[] params) {
        this.params = new ArrayList<>(Arrays.asList(params));
    }

    public CommandParameters(List<String> params) {
        this.params = params;
    }

    public String getFullRespond() {
        return params.stream().reduce("", (sum, item) -> sum+item+" ").trim();
    }

    public String getParamAt(int index) throws NoSuchCommandParamException {
        if(index>=params.size()) throw new NoSuchCommandParamException();
        return params.get(index);
    }

}