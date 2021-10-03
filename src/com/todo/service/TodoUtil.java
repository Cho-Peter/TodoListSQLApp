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
			System.out.printf("제목이 중복되었습니다. 제목이 중복될 수 없습니다.");
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
		list.addItem(t);
		System.out.println("할일이 추가되었습니다.");
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		
		System.out.print("\n"
				+ "[ 할일 삭제 ]\n"
				+ "삭제할 할일의 번호: ");
		
		int num = sc.nextInt();
		
		sc.nextLine();
		for (TodoItem item : l.getList()) {
			if (num == (l.indexOf(item)+1)) {
				System.out.println((l.indexOf(item)+1) + ". " + item.toString());
				System.out.print("위 항목을 삭제하시겠습니까? (y/n) : ");
				String q = sc.next();
				if(q.equals("y")) {
					l.deleteItem(item);
					System.out.println("삭제되었습니다.");
					break;
				}else break;
			}
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
		
		int i = 1;
		for (TodoItem item : l.getList()) {
			if (num == i) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_category, new_description, new_due_date);
				l.addItem(t);
				System.out.println("할일이 수정되었습니다.");
			}
			i++;
		}

	}
	
	public static void findItem(TodoList l, String key_word) {
		int count = 0;
		for(TodoItem item : l.getList()) {
			if(item.getTitle().contains(key_word)){
				System.out.println((l.indexOf(item)+1)+". " + item.toString());
				count++;
			}else if(item.getDesc().contains(key_word)) {
				System.out.println((l.indexOf(item)+1)+". " + item.toString());
				count++;
			}
		}
		System.out.println("총 " + count + "개의 항목을 찾았습니다.");
	}
	
	public static void findItemCate(TodoList l, String key_cate) {
		int count = 0;
		for(TodoItem item : l.getList()) {
			if(item.getCategory().contains(key_cate)) {
				System.out.println((l.indexOf(item)+1) + ". " + item.toString());
				count++;
			}
		}
		System.out.println("총 " + count + "개의 항목을 찾았습니다.");
	}
	
	public static void listCate(TodoList l) {
		HashSet <String> set = new HashSet<String>() ;
		for(TodoItem item: l.getList()) {
			set.add(item.getCategory());
		}
		Iterator iter = set.iterator();
		int i = 0;
		while(iter.hasNext()) {
			i++;
			String s = (String)iter.next();
			if(i == set.size()) System.out.println(s);
			else System.out.print(s + " / ");
		}
		System.out.println("총 " + set.size() + "개의 카테고리가 등록되어 있습니다.");
		
	}

	public static void listAll(TodoList l) {
		int count = 0;
		for (TodoItem item : l.getList()) count++;
		System.out.println("[전체 목록]" + ", 총 " + count + "개");
		for (TodoItem item : l.getList()) {
			System.out.println( (l.indexOf(item)+1) + ". " + item.toString());
		}
	}
	
	public static void saveList(TodoList l, String filename ) {
		try {
			Writer w = new FileWriter(filename);
			
			for(TodoItem item : l.getList()) {
				w.write(item.toSaveString());
			}
			w.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
