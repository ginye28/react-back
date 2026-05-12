package com.example.reactback.controller;

import com.example.reactback.dto.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet("/api/users")
public class UserController extends HttpServlet {
    private List<UserDto> users = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF8");
        resp.setCharacterEncoding("UTF8");
        String username = req.getParameter("username");

        ObjectMapper objectMapper = new ObjectMapper();
        if (users.stream().filter(user -> user.getUsername().equals(username)).count() < 1) {
            resp.setStatus(404);
            resp.getWriter().println(objectMapper.writeValueAsString(Map.of("message","조회된 사용자가 없습니다.")));
        } else {
            resp.setStatus(200);
            resp.getWriter().println(objectMapper.writeValueAsString(Map.of("message","해당 username의 사용자를 찾았습니다.")));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF8");
        resp.setCharacterEncoding("UTF8");
        BufferedReader br = req.getReader();
        String line = "";

        StringBuilder stringBuilder = new StringBuilder();
        while ((line = br. readLine()) != null) {
            stringBuilder.append(line);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        UserDto userDto = objectMapper.readValue(stringBuilder.toString(), UserDto.class);
        System.out.println(userDto.getUsername());

        resp.setStatus(200);
        resp.getWriter().println("사용자 등록 완료");
    }
}