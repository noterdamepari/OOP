package ru.nsu;

import java.util.HashMap;

/**
 * Класс памяти для хранения значений переменных.
 */
public class Memory {
    private final HashMap<String, Integer> mem = new HashMap<>();


    /**
     * Конструктор памяти.
     *
     * @param str строка где через ; перечисляются перменные и их значения в формате key=value.
     */
    public Memory(String str) {
        if (str.isBlank()) {
            return;
        }

        String[] vars = str.split(";");

        for (int i = 0; i < vars.length; i++) {
            vars[i] = vars[i].trim();
            String[] equal = vars[i].split("=");
            if (equal.length != 2) {
                throw new RuntimeException("Wrong string format.");
            }
            equal[0] = equal[0].trim();
            equal[1] = equal[1].trim();
            if (mem.containsKey(equal[0])) {
                throw new RuntimeException("Variable " + equal[0] + " is already set");
            }
            mem.put(equal[0], Integer.parseInt(equal[1]));
        }
    }

    /**
     * Получение значения переменной по имени.
     *
     * @param var имя переменной.
     */
    public int get(String var) {
        if (!mem.containsKey(var)) {
            throw new RuntimeException("Variable " + var + "not set");
        }
        return mem.get(var);
    }
}
