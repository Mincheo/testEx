package com.company;

import java.io.IOException;
import java.sql.SQLException;


public class Main {
    public static void main(String[] args) throws IOException, InterruptedException, SQLException {
        Api a = new Api();
        a.Start();
    }
}

//    Тестовое задание должно быть выполнено с использованием одного из следующих
//    языков программирования:
//        1. Java (либо иной язык программирования для JVM)
//        2. JavaScript/TypeScript
//        Тестовое задание должно быть выполнено с использованием одной из следующих
//        СУБД:
//        1. PostgreSQL
//        2. H2
//        Задание:
//        1. Реализовать алгоритм сортировки массива методом пузырька.
//        2. Реализовать веб-страницу, позволяющую ввести значения для сортировки, запустить
//        сортировку и вывести результат сортировки
//        3. Реализовать сохранение результата каждой сортировки в базе данных
//        4. При этом каждый элемент сортируемого массива должен храниться в отдельной
//        строке таблицы.
//        5. Реализовать возможность получения отсортированного массива путем выполнения
//        http-запроса. В запрос должен передаваться идентификатор сортировки.
//        6. Созданный проект залить на GitHub. В составе проекта должны быть все исходные
//        файлы, в том числе скрипт, с помощью которого создана БД (п. 3).