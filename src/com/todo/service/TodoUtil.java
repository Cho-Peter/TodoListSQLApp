package com.todo.service;

import java.util.*;
import java.util.HashSet;
import java.util.Iterator;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;



import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String title, category, desc, due_date;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "[ 할일 추가 ]\n"
				+ "할일의 제목: ");
		
		title = sc.nextLine();
		if (list.isDuplicate(title)) {
			System.out.println("제목이 중복되었습니다. 제목이 중복될 수 없습니다.");
			return;
		}
		
		sc.nextLine();
		System.out.print("카테고리: ");
		category = sc.nextLine();
		
		sc.nextLine();
		System.out.print("세부내용: ");
		desc = sc.nextLine();
		
		sc.nextLine();
		System.out.print("제출기한: ");
		due_date = sc.nextLine().trim();
		
		TodoItem t = new TodoItem(title, category, desc, due_date);
		if(list.addItem(t) > 0) {
			System.out.println("할일이 추가되었습니다.");
		}
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		
		System.out.print("\n"
				+ "[ 할일 삭제 ]\n"
				+ "삭제할 할일의 번호: ");
		
		int num = sc.nextInt();
		if(l.deleteItem(num) > 0) {
					System.out.println("삭제되었습니다.");
		}
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "[ 할일 수정 ]\n"
				+ "수정할 할일의 번호: ");
		
		int num = sc.nextInt();
		
		sc.nextLine();
		System.out.print("새로운 제목: ");
		String new_title = sc.nextLine();
		if (l.isDuplicate(new_title)) {
			System.out.println("제목이 중복되었습니다. 제목이 중복될 수 없습니다.");
			return;
		}
		
		sc.nextLine();
		System.out.print("새로운 카테고리: ");
		String new_category = sc.nextLine();
		
		
		sc.nextLine();
		System.out.print("새로운 세부내용: ");
		String new_description = sc.nextLine();
		
		sc.nextLine();
		System.out.print("새로운 제출기한: ");
		String new_due_date = sc.nextLine().trim();
		
		TodoItem t = new TodoItem(new_title, new_category, new_description, new_due_date);
		t.setId(num);
		
		if(l.updateItem(t)>0) {
			System.out.println("할일이 수정되었습니다.");
		}
	}
	
	public static void findList(TodoList l, String key_word) {
		int count = 0;
		for(TodoItem item : l.getList(key_word)) {
			System.out.println(item.toString());
			count++;
		}
		System.out.printf("총 %d개의 항목을 찾았습니다.", count);
		System.out.println("");
	}

	public static void findListCate(TodoList l, String key_cate) {
		int count = 0;
		for(TodoItem item : l.getListCategory(key_cate)){
			System.out.println(item.toString());
			count++;
		}
		System.out.printf("총 %d개의 항목을 찾았습니다.", count);
		System.out.println("");
	}
	
	public static void listCate(TodoList l) {
		int count = 0;
		for(String item : l.getCategories()){
			System.out.print(item + " ");
			count++;
		}
		System.out.printf("총 %d개의 카테고리가 등록되어 있습니다.", count);
		System.out.println("");
		
	}

	public static void listAll(TodoList l) {
		System.out.printf("[전체 목록], 총 %d개\n", l.getCount());
		for (TodoItem item : l.getList()) {
			System.out.println(item.toString());
		}
	}
	
	public static void listAll(TodoList l, String orderby, int ordering){
		System.out.printf("[전체 목록], 총 %d개\n", l.getCount());
		for(TodoItem item : l.getOrderedList(orderby, ordering)){
			System.out.println(item.toString());
		}
	}
	
	public static void comp(TodoList l, int key){
		if(l.completeItem(key) > 0) {
			System.out.println("완료체크 했습니다.");
		}
	}

	public static void ls_comp(TodoList l){
		int count = 0;
		for(TodoItem item : l.getCompletedItems()){
			System.out.println(item.toString());
			count++;
		}
		System.out.printf("총 %d개를 완료했습니다.", count);
		System.out.println("");
	}
	
	public static void loadList(TodoList l, String filename) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			
			String oneline;
			int i = 0;
			while((oneline = br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(oneline,"##");
				String category = st.nextToken();
				String title = st.nextToken();
				String desc = st.nextToken();
				String due_date = st.nextToken();
				String date = st.nextToken();
				
				TodoItem t = new TodoItem(title, category, desc, due_date);
				t.setCurrent_date(date);
				l.addItem(t);
				i++;
			}
			br.close();
			System.out.println(i + "개의 항목을 읽었습니다.");
			
		} catch (FileNotFoundException e) {
			System.out.println("todolist.txt 파일이 없습니다.");
			//e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
