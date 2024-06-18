package com.pluralsight.views;

public class HomeView extends ViewBase
{
    public int getUserSelection()
    {
        System.out.println();
        System.out.println("Sakila Home");
        System.out.println("-".repeat(40));
        System.out.println("1) Show Actors");

        System.out.println("0) Exit");
        System.out.println();
        System.out.print("Please select an option: ");

        return Integer.parseInt(in.nextLine());
    }
}
