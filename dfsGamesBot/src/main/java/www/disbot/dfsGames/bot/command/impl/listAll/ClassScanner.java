package www.disbot.dfsGames.bot.command.impl.listAll;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;

import www.disbot.dfsGames.bot.command.Command;

public class ClassScanner {
	public 	List<Class<?>> findAllCommands(Package pack) {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		
		String basePackageName = pack.getName();
	   	String path = basePackageName.replace('.', '/');
	   	
	   	List<Class<?>> commandClassList = new ArrayList<>();

	   	try {
	   		List<File> files = new ArrayList<File>();
			Enumeration<URL> resources = classLoader.getResources(path);
			while (resources.hasMoreElements()) {
				URL resource = resources.nextElement();
				files.add(new File(resource.getFile()));
			}
			for (File file : files) {
				if (file.isDirectory()) {
					commandClassList.addAll(findCommands(file, basePackageName));
				}
			}
		}
	   	catch (IOException e) {
			e.printStackTrace();
		}
	   	return commandClassList;
	}

	private Collection<Class<?>> findCommands(File directory, String packageName) {
		List<Class<?>> classList = new ArrayList<>();
		
		if (!directory.exists()) {
			return classList;
		}

		File[] files = directory.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				classList.addAll(findCommands(file, packageName + "." + file.getName()));
			}
			else if (file.getName().endsWith(".class")) {
				String className = packageName + '.' + file.getName().substring(0, file.getName().length() - 6);
				ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
				try {
					Class<?> target = Class.forName(className, false, classLoader);
					
					List<Class<?>> implInterfaceList = Arrays.asList(target.getInterfaces());
					if (implInterfaceList.contains(Command.class)) {
						classList.add(target);
					}
					
					Class<?> superClass = target.getSuperclass();
					if (Arrays.asList(superClass.getInterfaces()).contains(Command.class)) {
						classList.add(target);
					}
					
				}
				catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}

		return classList;
	}
}
