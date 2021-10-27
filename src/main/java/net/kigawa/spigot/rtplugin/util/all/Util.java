package net.kigawa.spigot.rtplugin.util.all;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.util.*;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class Util {
    public static <T> void executeProcesses(Collection<T> collection, Process<T> process) {
        for (T t : collection) {
            process.execute(t);
        }
    }

    public static String createString(int[] ints) {
        StringBuilder str = new StringBuilder(Integer.toString(ints[0]));
        for (int i = 1; i < ints.length; i++) {
            str.append(", ").append(ints[i]);
        }
        return str.toString();
    }

    public static <T> List<T> changeListType(List list, Class<T> type) {
        List<T> list1 = new ArrayList<>();
        for (Object o : list) {
            list1.add((T) o);
        }
        return list1;
    }

    public static int[] getIntegerArrangement(List<Integer> list) {
        int[] ints = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ints[i] = list.get(i);
        }
        return ints;
    }

    public static String[] removeStrSet(String[] strings, String s) {
        return addSet(strings, s, new NewStrArrangement());
    }

    public static String[] addStrSet(String[] strings, String s) {
        return addSet(strings, s, new NewStrArrangement());
    }

    public static <T> T[] removeSet(T[] ar, T t, NewArrangement<T> newArrangement) {
        Set<T> set = getSet(ar);
        set.remove(t);
        return getArrangement(set, newArrangement);
    }

    public static <T> T[] addSet(T[] ar, T t, NewArrangement<T> newArrangement) {
        Set<T> set = getSet(ar);
        set.add(t);
        return getArrangement(set, newArrangement);
    }

    public static String[] getStringArrangement(List<String> list) {
        return getArrangement(list, (int i) -> new String[i]);

    }

    public static <T> T[] getArrangement(Collection<T> collection, NewArrangement<T> newArrangement) {
        T[] ts = newArrangement.getArrangement(collection.size());
        int i = 0;
        for (T t : collection) {
            ts[i] = t;
            i++;
        }
        return ts;
    }

    public static <T> Set<T> getSet(T[] ts) {
        Set<T> set = new HashSet<>();
        Collections.addAll(set, ts);
        return set;
    }

    public static <T> List<T> getList(T[] o) {
        List<T> list = new ArrayList<>();
        Collections.addAll(list, o);
        return list;
    }

    public static void runCommand(String[] command, File dir) {
        dir.mkdirs();
        System.out.println("run jar...");
        String result;
        java.lang.Process process = null;
        Runtime runtime = Runtime.getRuntime();
        try {
            process = runtime.exec(command, null, dir);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("out put log...");
        InputStream inputStream = process.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        try {
            while ((result = bufferedReader.readLine()) != null) {
                System.out.println(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void download(URL url, File file, String name) throws IOException {
        String path = url.getPath();
        File file1 = new File(file, name);

        if (file1.exists()) {
            file1.delete();
        }

        Files.copy(url.openStream(), file1.toPath(), REPLACE_EXISTING);
    }

    public interface Process<T> {
        void execute(T t);
    }

    public interface NewArrangement<T> {
        T[] getArrangement(int size);
    }

    public static class NewStrArrangement implements NewArrangement<String> {
        @Override
        public String[] getArrangement(int size) {
            return new String[size];
        }
    }
}