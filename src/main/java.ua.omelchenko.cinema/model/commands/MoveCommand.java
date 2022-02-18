package model.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MoveCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return (String) request.getSession().getAttribute("URI");
    }
}
