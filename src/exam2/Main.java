package exam2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dto.Article;
import util.Util;

public class Main {
	static int id = 1;
	static List<Article> articles = new ArrayList<>();
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		makeTestData();
		while(true) {
			System.out.print("명령어) ");
			String cmd = sc.nextLine();
			
			
			if(cmd.equals("exit")) {
				break;
			}
			
			if(cmd.length() == 0) {
				System.out.println("명령어를 입력하지 않았습니다.");
				continue;
			}
			
			
			//게시글 목록
			else if(cmd.startsWith("article list")) {
				if(articles.size() == 0) {
					System.out.println("작성된 글이 없습니다.");
					continue;
				}
				
				String keyword = cmd.substring("article list".length()).trim();
				List <Article> testArticle = articles;
				
				
				if(keyword.length() > 0) {
					testArticle = new ArrayList<>();
					for(Article article:articles) {
						if(article.getTitle().contains(keyword)) {
							testArticle.add(article);
						}
					}
					if(testArticle.size() == 0) {
						System.out.println("해당되는 글이 없습니다.");
						continue;
					}
				}
					
				for(Article article: testArticle) {
					System.out.println("번호	|작성일	|제목	|내용");
					System.out.printf("%d	|%s	|%s	|%s\n", article.getId(), article.getTitle(),  article.getBody(), article.getRegDate());
				}	
				
			}
			
			//게시글 작성		
			else if(cmd.equals("article write")) {
				System.out.print("글제목: ");
				String title = sc.nextLine();
				System.out.print("글내용: ");
				String body = sc.nextLine();
				
				articles.add(new Article(id++, Util.getDateStr(), title, body));
				System.out.printf("%d번 게시물 작성 완료\n", id);
			}
			
			//게시글 상세
			else if(cmd.startsWith("article detail")) {
				int i = check(cmd);
				
				if(i == 0) {
					System.out.println("잘못된 형식입니다.");
					continue;
				}
				
				
				Article findArticle = findArticleById(i);
			
				if(findArticle == null) {
					System.out.println("잘못됭 형식입니다.");
					continue;
				}
				
				System.out.println("글 번호: " + findArticle.getId());
				System.out.println("글 제목: " + findArticle.getTitle());
				System.out.println("글 내용: " + findArticle.getBody());
				System.out.println("글 작성시간: " + findArticle.getRegDate());
			}
			
			
			//게시글 수정
			else if(cmd.startsWith("article modify")) {
				int i = check(cmd);
				
				if(i == 0) {
					System.out.println("잘못된 형식입니다.");
					continue;
				}
				
				
				Article findArticle = findArticleById(i);
			
				if(findArticle == null) {
					System.out.println("잘못됭 형식입니다.");
					continue;
				}
				
				System.out.print("수정할 글 제목: ");
				String title = sc.nextLine();
				System.out.print("수정할 글 내용: ");
				String body = sc.nextLine();
				
				findArticle.setTitle(title);
				findArticle.setBody(body);
				System.out.println("수정이 완료되었습니다.");
				
			}
			
			//게시글 삭제
			else if(cmd.startsWith("article delete")) {
				int i = check(cmd);
				
				if(i == 0) {
					System.out.println("잘못된 형식입니다.");
					continue;
				}
				
				
				Article findArticle = findArticleById(i);
			
				if(findArticle == null) {
					System.out.println("잘못됭 형식입니다.");
					continue;
				}
				
				articles.remove(findArticle);
				System.out.printf("%d번 게시물 삭제 완료", findArticle.getId());
			}
			
			//아에 잘못 적었을 때
			else {
				System.out.println("잘못된 명령입니다.");
				continue;
			}
		}
		System.out.println("프로그램을 종료합니다.");
		sc.close();
		
	}
	static void makeTestData(){
		System.out.println("3개의 테스트 데이터 생성완료");
		for(int i=1; i<=3; i++) { 
			articles.add(new Article(id++, Util.getDateStr(), "제목" + i, "내용"+ i));
		}
	}
	
	static int check(String cmd){
		int i = 0;
		String []cmdBits = cmd.split(" ");
		try {
			i = Integer.parseInt(cmdBits[2]);
		}
		catch(Exception e) {
			return 0;
		}
		return i;
	}
	
	static Article findArticleById(int i) {
		
		Article findArticle = null;
		
		for(Article article : articles) {
			if(article.getId() == i) {
				findArticle = article;
			}
		}
		return findArticle;

	}
}
