package com.todo.menu;
public class Menu {

    public static void displaymenu()
    {
        System.out.println("TodoList 기본 메뉴");
        System.out.println("1. 할일 추가 ( add )");
        System.out.println("2. 할일 삭제 ( del )");
        System.out.println("3. 할일 수정  ( edit )");
        System.out.println("4. 할일 보기 ( ls )");
        System.out.println("5. 제목 순으로 정렬 ( ls_name_asc )");
        System.out.println("6. 제목 역순으로 정렬 ( ls_name_desc )");
        System.out.println("7. 날짜 순으로 정렬 ( ls_date )");
        System.out.println("8. 종료 (exit)");
    }
    public static void prompt() {
        System.out.println("사용할 메뉴를 입력하시오 >");
    }
}
