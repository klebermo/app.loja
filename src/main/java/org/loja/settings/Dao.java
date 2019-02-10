package org.loja.settings;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.io.File;
import java.io.InputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public abstract class Dao<E> {

	Class<E> clazz;

	Path dir, file;

	public Dao(Class<E> clazz) {
		this.clazz = clazz;

		String dir_name = System.getProperty("user.home")+File.separator+".app"+File.separator+"Config";
		try {
			dir = Paths.get(dir_name);
			if(!Files.exists(dir))
				Files.createDirectories(dir);
		} catch (Exception e) {
			e.printStackTrace();
		}

		String file_name = dir_name + File.separator + clazz.getSimpleName() + ".properties";
		try {
			file = Paths.get(file_name);
			if(!Files.exists(file))
				Files.createFile(file);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			InputStream fileIn = Files.newInputStream(file);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			in.close();
			fileIn.close();
		} catch (Exception e) {
			try {
				E object = clazz.newInstance();
				OutputStream fileOut = Files.newOutputStream(file);
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				out.writeObject(object);
				out.close();
				fileOut.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	public E get() {
		E object = null;
		try {
			object = clazz.newInstance();
			InputStream fileIn = Files.newInputStream(file);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			object = (E) in.readObject();
			in.close();
			fileIn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return object;
	}

	public void set(E object) {
		try {
			OutputStream fileOut = Files.newOutputStream(file);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(object);
			out.close();
			fileOut.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
