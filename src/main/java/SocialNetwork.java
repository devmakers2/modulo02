package main.java;

import main.java.exceptions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SocialNetwork {
	private List<Post> posts = new ArrayList<>();
	private List<Profile> profiles = new ArrayList<>();

	public void run() {
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
					userMenu(profile);
				} catch (NoRegisteredUsersException | UserNotFoundException | InvalidPasswordException e) {
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
		
		throw new UserNotFoundException();
	}
	
	private void userMenu(Profile profile) {
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
					profile.addAPost();
					System.out.println("==========");
					break;
				case 'T':
					System.out.println("----------");
					profile.printPosts();
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
}
