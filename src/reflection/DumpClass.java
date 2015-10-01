/**
 * 
 */
package reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Scanner;
import java.util.StringTokenizer;


/**
 * @author Giuseppe Fusco (giuseppe.fusco.666@gmail.com)
 *
 */
public class DumpClass {

	//	private static boolean implementsInterface(Class<?> c, Class<?> in){
	//		List<Class<?>> l = getAllInterface(c);
	//		for(int i=0; i<l.size(); i++)
	//			if(l.get(i)==in)
	//				return true;
	//		return false;
	//	}

	//	private static List<Class<?>> getAllInterface(Class<?> c){
	//		List<Class<?>> l = new ArrayList<Class<?>>();
	//		Class<?>[] i = c.getInterfaces();
	//		if(i.length>=0){
	//			if(c.isInterface())
	//				l.add(c);
	//			else;
	//			for(int k=0; k<i.length; k++)
	//				l.addAll(getAllInterface(i[k]));
	//		}
	//		return l;
	//	}

	// -f
	public static void dumpField(Class<?> clazz, int innerLevel){
		System.out.print(getTabByLevel(innerLevel*3)+"******   FIELDS   ******\n\n");
		Field[] fields = clazz.getDeclaredFields();
		for(int i=0; i<fields.length; i++){
			Field f = fields[i];
			Annotation[] annot = f.getDeclaredAnnotations();
			for(int k=0; k<annot.length; k++){
				Annotation a = annot[k];
				System.out.print("\n"+getTabByLevel(innerLevel)+"@ "+a.annotationType().getName()+
						"\n");
			}
			System.out.print(getTabByLevel(innerLevel)+decodeModifier(f.getModifiers())+
					" "+getType(f.getType())+
					" "+f.getName().replace('$', '.')+"\n");
		}
	}
	
	private static String getType(Class<?> c){
		if(c.isArray()){
			StringBuilder s = new StringBuilder();
			s.append("array {");
			Class<?> ct = c.getComponentType();
			if(ct.isArray())s.append(getType(ct));
			else
				s.append(" "+ct.getName().replace('$', '.'));

			s.append(" }");
			return s.toString();
		}
		else return c.getName().replace('$', '.');
	}

	private static String decodeModifier(int modifierCode){
		return Modifier.toString(modifierCode);
	}

	// -c
	public static void dumpConstructor(Class<?> clazz, int innerLevel){
		Constructor<?>[] cons = clazz.getDeclaredConstructors();
		System.out.print(getTabByLevel(innerLevel*3)+"******   CONSTRUCTORS   ******\n");
		for(int i=0; i<cons.length; i++){
			Constructor<?> c = cons[i];
			Annotation[] annot = c.getDeclaredAnnotations();
			for(int k=0; k<annot.length; k++){
				Annotation a = annot[k];
				System.out.print("\n"+getTabByLevel(innerLevel)+"@ "+a.annotationType().getName());
			}
			System.out.print("\n");
			System.out.print(getTabByLevel(innerLevel)+decodeModifier(c.getModifiers())+
					" "+c.getName().replace('$', '.')+
					" (\n");
			Class<?>[] param = c.getParameterTypes();
			for(int k=0; k<param.length; k++){
				Class<?> cc = param[k];
				System.out.print(getTabByLevel(innerLevel)+"\t"+getType(cc)+
						",\n");
			}
			System.out.print(getTabByLevel(innerLevel)+"\t)");
			Class<?>[] exc = c.getExceptionTypes();
			if(exc.length>0){
				System.out.print(" throws ");
				for(int k=0; k<exc.length; k++){
					Class<?> cc = exc[k];
					System.out.print(cc.getName()+", ");
				}
			}
			System.out.print("\n\n");
		}
	}

	// -m
	public static void dumpMethod(Class<?> clazz, int innerLevel) {
		System.out.print(getTabByLevel(innerLevel*3)+"******   METHODS   ******\n");
		Method[] met = clazz.getDeclaredMethods();
		for(int i=0; i<met.length; i++){
			Method m = met[i];
			Annotation[] annot = m.getDeclaredAnnotations();
			for(int k=0; k<annot.length; k++){
				Annotation a = annot[k];
				System.out.print("\n"+getTabByLevel(innerLevel)+"@ "+a.annotationType().getName().replace('$', '.'));
			}
			Object defValue = m.getDefaultValue();
			if(defValue!=null)
				System.out.print("\n"+getTabByLevel(innerLevel)+"Default value: "+defValue.getClass()+" = "+defValue.toString());
			System.out.print("\n");
			System.out.print(getTabByLevel(innerLevel)+decodeModifier(m.getModifiers())+
					" "+getType(m.getReturnType())+
					" "+m.getName()+
					" (\n");
			Class<?>[] param = m.getParameterTypes();
			for(int k=0; k<param.length; k++){
				Class<?> c = param[k];
				System.out.print(getTabByLevel(innerLevel)+"\t"+getType(c)+
						",\n");
			}
			System.out.print(getTabByLevel(innerLevel)+"\t)");
			Class<?>[] exc = m.getExceptionTypes();
			if(exc.length>0){
				System.out.print(" throws ");
				for(int k=0; k<exc.length; k++){
					Class<?> c = exc[k];
					System.out.print(c.getName()+", ");
				}
			}
			System.out.print("\n\n");
		}
	}

	// -h
	public static void dumpHierarchy(Class<?> clazz, int innerLevel){
		System.out.print(getTabByLevel(innerLevel*3)+"******   HIERARCHY   ******\n");
		System.out.print(getTabByLevel(innerLevel)+clazz.getName().replace('$', '.'));
		Class<?> sup = clazz.getSuperclass();
		int level = innerLevel;
		while(sup!=null){
			System.out.print("\n"+getTabByLevel(++level)+"-->\t"+getType(sup));
			sup = sup.getSuperclass();
		}
	}

	// -i
	public static void dumpInterface(Class<?> clazz, int innerLevel){
		System.out.print(getTabByLevel(innerLevel*3)+"******   INTERFACE   ******\n");
		Class<?>[] it = clazz.getInterfaces();
		if(it.length>0){
			for(int i=0; i<it.length; i++){
				Class<?> c = it[i];
				System.out.print(getTabByLevel(innerLevel)+c.getName().replace('$', '.')+"\n");
				int level = 0;
				Class<?>[] sup = c.getInterfaces();
				for(int j=0; j<sup.length; j++){
					System.out.print(getTabByLevel(innerLevel));
					getAllInterface(sup[j], ++level);
				}
				System.out.print("\n");
			}	
		}

	}

	private static void getAllInterface(Class<?> c, int level){
		Class<?>[] i = c.getInterfaces();
		if(i.length>=0){
			if(c.isInterface())
				System.out.print(getTabByLevel(level)+"-->\t"+c.getName()+"\n");
			else;
			for(int k=0; k<i.length; k++)
				getAllInterface(i[k], ++level);
		}
	}

	private static String getTabByLevel(int level){
		StringBuilder s = new StringBuilder();
		for(int i=0; i<level; i++)
			s.append("\t");
		return s.toString();
	}

	// -a
	public static void dumpAll(Class<?> clazz){
		dumpAll(clazz, 0);
	}

	private static void dumpAll(Class<?> clazz, int innerLevel){
		String name = clazz.getName();
		if(clazz.isMemberClass()){
			System.out.print(getTabByLevel(innerLevel)+decodeModifier(clazz.getModifiers())+(!clazz.isInterface()?" class ":" ")+
					name.replace('$', '.')+ " {\n");
		}else{
			System.out.print(decodeModifier(clazz.getModifiers())+(!clazz.isInterface()?" class ":" ")+
					name+ " {\n");
		}

		innerLevel++;

		System.out.println();
		dumpField(clazz,innerLevel);
		System.out.println();
		dumpMethod(clazz,innerLevel);
		dumpConstructor(clazz,innerLevel);
		dumpHierarchy(clazz,innerLevel);
		System.out.println();
		System.out.println();
		dumpInterface(clazz,innerLevel);


		Class<?>[] decClass = clazz.getDeclaredClasses();
		for(int i=0; i<decClass.length; i++){
			Class<?> c = decClass[i];
			System.out.print("\n"+getTabByLevel(innerLevel*3)+"******   MEMBER CLASS   ******\n");
			dumpAll(c,innerLevel);
		}
		//		Object[] enumConst = clazz.getEnumConstants();
		//		if(enumConst!=null)
		//			for(int i=0; i<enumConst.length; i++){
		//				Object c = enumConst[i];
		//				System.out.print("\n"+getTabByLevel(innerLevel*3)+"******   ENUM CONSTANTS   ******\n");
		//				dumpAll(c.getClass(),innerLevel);
		//			}
		System.out.print("\n"+getTabByLevel(innerLevel)+"}");
	}

	public static String help(){
		StringBuilder s = new StringBuilder();
		s.append("\nUsage: q | -class classToDump [- a | - fcmhi]\n\n");
		s.append("classToDump is class file\n\n");
		s.append("- a\tprint all meta-information\n");
		s.append("- fcmhi:\n");
		s.append("\tf\tview fields\n");
		s.append("\tc\tview constructors\n");
		s.append("\tm\tview methods\n");
		s.append("\th\tview hierarchy\n");
		s.append("\ti\tview interfaces\n");
		s.append("\tq\tquit\n");
		s.append("\n");
		return s.toString();
	}

	public static void main(String[] args) throws Exception {
		System.out.print(help()+"\nDump >> ");
		Scanner sc = new Scanner(System.in);
		String line = sc.nextLine();
		parse(line, sc);
	}

	private static void parse(String line, Scanner sc) {
		if(!line.equals("q")){
			if(line.startsWith("-class")){
				StringTokenizer tok = new StringTokenizer(line, " ");
				if(tok.countTokens()==3){
					tok.nextToken();
					String clazz = tok.nextToken();
					String opt = tok.nextToken();
					try {
						Class<?> c = Class.forName(clazz);
						if(opt.equals("-a")){
							dumpAll(c);
						}else{
							char[] ch = opt.toCharArray();
							for(int i=0; i<ch.length; i++){
								char ct = ch[i];
								if(ct=='f')dumpField(c, 0);
								else if(ct=='m')dumpMethod(c, 0);
								else if(ct=='c')dumpConstructor(c, 0);
								else if(ct=='i')dumpInterface(c, 0);
								else if(ct=='h')dumpHierarchy(c, 0);
								System.out.println();
							}
						}

						System.out.print("\nDump >> ");
						parse(sc.nextLine(), sc);
					} catch (ClassNotFoundException e) {
						System.out.print("\nClass not found.\nDump >> ");
						parse(sc.nextLine(), sc);
					}
				}else
					{
					System.out.print(help()+"\nDump >> ");
					parse(sc.nextLine(), sc);
					}
			}
			else
				{
				System.out.print(help()+"\nDump >> ");
				parse(sc.nextLine(), sc);
				}
		}
		System.exit(0);

	}

}
