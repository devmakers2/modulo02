package main.java;

import java.util.Objects;
import java.util.Scanner;

public class Profile {
	private String name;
	private String username;
	private String password;

	Profile(String name, String username, String password) {
		this.name = name;
		this.username = username;
		this.password = password;
	}
	
	protected Post createPost() {
		System.out.println("por favos informe os dados do post:");
		Scanner scanner = new Scanner(System.in);

		System.out.print("data (exemplo: “22/11/2022”): ");
		String date = scanner.nextLine();

		System.out.print("hora (exemplo: “22:38”): ");
		String hour = scanner.nextLine();

		System.out.print("conteúdo: ");
		String content = scanner.nextLine();

		return new Post(date, hour, content, this.username);
	}
	
	protected boolean isPasswordEqualTo(String password) {
		return this.password.equals(password);
	}
	
	protected String getName() {
		return this.name;
	}
	
	protected String getUsername() {
		return this.username;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, password, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Profile other = (Profile) obj;
		return Objects.equals(name, other.name) && Objects.equals(password, other.password)
				&& Objects.equals(username, other.username);
	}
}
