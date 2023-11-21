package SiAOD.Graph;
import java.util.Scanner;
import java.util.InputMismatchException;

public class TestMain
{
    public static void main(String[] args)
    {
        try (Scanner scanner = new Scanner(System.in))
        {
            Graph graph = new Graph();
            int number, from, to, weight;
            while (true)
            {
                System.out.print("Введите узел графа или 'stop' для завершения: ");
                if (scanner.hasNextInt())
                {
                    number = scanner.nextInt();
                    graph.addGraph(number);
                }
                else
                {
                    String input = scanner.next();
                    if (input.equals("stop"))
                        break;
                }
            }
            System.out.println("\nДобавьте связи между узлами графа или stop.\n");
            while (true)
            {
                System.out.print("Из какого узла: ");
                if (scanner.hasNextInt())
                {
                    from = scanner.nextInt();
                    System.out.print("К какому узлу идёт ребро: ");
                    to = scanner.nextInt();
                    System.out.print("Вес ребра: ");
                    weight = scanner.nextInt();
                    System.out.println(graph.addEdge(from, to, weight));
                }
                else
                {
                    String input = scanner.next();
                    if (input.equals("stop"))
                        break;
                }
            }
            System.out.print("Вывести иерархию узлов дерева решений? (Да / Нет): ");
            if (scanner.next().equals("Да"))
            {
                System.out.print("От какого узла?: ");
                to = scanner.nextInt();
                graph.printTree(to);
            }
            System.out.print("Кратчайший путь от какого узла? ");
            from = scanner.nextInt();
            System.out.print("Куда? ");
            to = scanner.nextInt();
            System.out.print("\nКратчайший путь: " + graph.decisionTreeWay(from, to));
        }
        catch (InputMismatchException | IllegalArgumentException exc)
        {
            System.out.println(exc);
            System.exit(1);
        }
    }
}