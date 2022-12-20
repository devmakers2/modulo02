package main.java;

import main.java.exceptions.EmptyFieldException;
import main.java.exceptions.InvalidPasswordException;
import main.java.exceptions.NoRegisteredUsersException;
import main.java.exceptions.UsernameNotFoundException;
import main.java.exceptions.UsernameNotAvailableException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SocialNetwork {
	private List<Post> posts = new ArrayList<>();
	private List<Profile> profiles = new ArrayList<>();

	protected void run() {
		System.out.println("escolha uma opção:");
		System.out.println("\"C\" para cadastrar-se");
		System.out.println("\"E\" para entrar");
		System.out.println("\"F\" para fechar");
		
		Scanner scanner = new Scanner(System.in);
		char option = scanner.next().charAt(0);
		
		switch (option) {
			case 'C':
				try {
					registerProfile();
				} catch (EmptyFieldException | UsernameNotAvailableException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 'E':
				try {
					Profile profile = login();
					loggedUserMenu(profile);
				} catch (NoRegisteredUsersException | UsernameNotFoundException | InvalidPasswordException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 'F':
				System.out.println("até mais");
				return;
			default:
				System.out.println("opção inválida");
		}
		
		System.out.println("==========");		
		run();
	}

	private void registerProfile() {
		Scanner scanner = new Scanner(System.in);		

		System.out.println("----------");
		System.out.println("por favos informe seus dados:");
		System.out.print("nome: ");
		String name = scanner.nextLine();
		if (name.isEmpty()) {
			throw new EmptyFieldException();
		}
		
		System.out.print("nome de usuário: ");
		String username = scanner.nextLine();
		if (username.isEmpty()) {
			throw new EmptyFieldException();
		}
		
		if (profiles.stream().anyMatch(profile -> profile.getUsername().equals(username))) {
			throw new UsernameNotAvailableException();
		}
		
		System.out.print("senha: ");
		String password = scanner.nextLine();
		if (password.isEmpty()) {
			throw new EmptyFieldException();
		}

		profiles.add(new Profile(name, username, password));
		System.out.println("cadastro realizado com sucesso");
	}
	
	private Profile login() {
		System.out.println("----------");

		if (profiles.isEmpty()) {
			throw new NoRegisteredUsersException();
		}
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("digite seu nome de usuário: ");
		String username = scanner.nextLine();
		
		Profile profile = getProfileFromUsername(username);

		System.out.print("digite sua senha: ");
		String password = scanner.nextLine();		
		if (!profile.isPasswordEqualTo(password)) {
			throw new InvalidPasswordException();
		}

		return profile;		
	}
	
	private Profile getProfileFromUsername(String username) {
		for (Profile profile : profiles) {
			if (profile.getUsername().equals(username)) {
				return profile;
			}
		}
		
		throw new UsernameNotFoundException();
	}
	
	private void loggedUserMenu(Profile profile) {
		System.out.println("----------");
		System.out.println("Bem-vindo, " + profile.getName() + ".");
		
		while (true) {
			System.out.println("escolha uma opção:");
			System.out.println("\"P\" para postar");
			System.out.println("\"T\" para timeline");
			System.out.println("\"S\" para sair");
			
			Scanner scanner = new Scanner(System.in);
			char option = scanner.next().charAt(0);
			
			switch (option) {
				case 'P':
					System.out.println("----------");
					Post newPost = profile.createPost();
					this.posts.add(newPost);
					System.out.println("==========");
					break;
				case 'T':
					System.out.println("----------");
					List<Post> loggedUserPosts = this.posts.stream().
							                                filter(post -> post.getAuthor().equals(profile.getUsername())).
							                                collect(Collectors.toList());
					this.printPosts(loggedUserPosts);
					System.out.println("==========");
					break;
				case 'S':
					return;
				default:
					System.out.println("opção inválida");
					System.out.println("----------");
			}
		}
	}

	private void printPosts(List<Post> posts) {
		for (Post post : posts) {
			System.out.println(post.getFormattedPost());
		}
	}
}
