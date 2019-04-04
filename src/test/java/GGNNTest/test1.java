package src;

import src.p3.C;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Vector;

public class test1 {
	public static void main(String[] args) {
		if (args[0] == "1")
			m1(1);
		else
			return;
		return;
	}

	public static void m1(int arg) {
		int i = 9;
		while(arg>10) {
			do {
				if(i>1)
					i--;
				else
					i++;
				System.out.print(i);
				i = arg+i;
			}while(i>10);
			arg++;
		}
		i+=1;
		arg++;
		return;
	}
	HashSet<String> ints = new HashSet<>();
	void addint(String i) {
		this.ints.add(i);
	}

}
