package dsa;
import java.util.Scanner;
import javax.sound.sampled.SourceDataLine;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
class Timetable_project
{

    ArrayList<Node> arr=new ArrayList<Node>(); //adjacency list that stores the connected graph of all subjects
    static class Node //each node has a subname (subject name) associated with it and a next node that it points to
    {
        String subname;
        Node next;
        Node(String subname) 
        {
            this.subname=subname;
            next=null;
        }
        Node()
        {
            subname=null;
            next=null;
        }
    }
    
    static Scanner src=new Scanner(System.in); 
    public void create() //creates the graph from given csv file
    {
        String csvFile = "E:/project/dsa/courses.csv"; //file path for the csv file
		readFile reader = new readFile(); //create an object of class readFile
        ArrayList<List<String>> data=reader.readFile1(csvFile);  //store csv data as an arraylist of list
        int nyear = data.size(); //number of batches 
        System.out.println("Arraylist of courses and subjects");
        System.out.println(data.toString()); //print arraylist of lists
        System.out.println();
    
        for(int i=0;i<nyear;i++) //creating a list for each unique subjects
        	                     
        {
            for(int j=1;j<data.get(i).size();j++)
            {
                int flag=0;
                for(int k=0;k<arr.size();k++)
                {
                    if((arr.get(k).subname).equals(data.get(i).get(j)))
                    {
                        flag=1;
                        break;
                    }
                }
                if(flag==0)
                {
                    Node new_node=new Node(data.get(i).get(j));
                    arr.add(new_node);
                }
             }
           
        }
        System.out.println("Unique subjects (vertices) : ");
        for(int p=0;p<arr.size();p++) //prints all unique subjects (vertices of our graph)
        {
            System.out.print(arr.get(p).subname+" ");
        }
       System.out.println();
       
        for(int i=0;i<data.size();i++)//edges are added between all subjects for the same batch
        {
            for(int j=1;j<data.get(i).size();j++)
            {
                for(int k=j+1;k<data.get(i).size();k++)
                {
                    insert(data.get(i).get(j),data.get(i).get(k));
                    insert(data.get(i).get(k),data.get(i).get(j));

                }
            }
        }

    }

    public void insert(String u,String v) //passing two subjects that have an edge between them
    {
        Node temp=new Node(v);
        
        for(int a=0;a<arr.size();a++) //find index of source subject
        {

            if(arr.get(a).subname.equals(u))
            {
                int flag=0;
                Node ptr=arr.get(a);
                while(ptr.next!=null)
                {
                    if(ptr.subname.equals(v)) //find index of destination subject
                    {
                        flag=1; 
                        break;
                    }
                    
                    ptr=ptr.next;
                }
                if(flag==0)
                ptr.next=temp;

            }
        }
    }

    public void display()   //To display the adjancency list
    {
        System.out.println();
        System.out.println("The subject list: ");
        for(int h=0;h<arr.size();h++)   //for traversal of all the subjects present in the list
        {
            System.out.print(arr.get(h).subname);
            Node temp=arr.get(h).next;
            while(temp!=null)
            {
                System.out.print(" --> "+temp.subname); //Displaying the list of adjacent vertices for each subject
                temp=temp.next;
            }
            System.out.println(" ");

        }
        System.out.println();
    }

    public void colour()
    {
        int s=arr.size();
        int res[]=new int[s];
        char ava[]=new char[s];
        for(int i=0;i<s;i++)//initializing the res array to -1
        {
            res[i]=-1;
        }
        for(int i=0;i<s;i++)//initializing the ava array to 'T'
        {
            ava[i]='T';
        }
        res[0]=0;
        for(int k=1;k<s;k++)
        {
            Node temp=arr.get(k).next;
            while(temp!=null)// Assigning colours to different nodes considering no to adjacent vertices can have the same colour
            {
                for(int p=0;p<s;p++)
                {
                    if(arr.get(p).subname.equals(temp.subname))
                    {
                        if(res[p]!=(-1))//If the colour is already assigned to a node then it can't be assigned to its adjacent nodes
                                        //Hence marking the colours which are not available in ava array
                        {
                            ava[res[p]]='F';
                        }
                        break;
                    }
                }
                temp=temp.next;
            }
            for(int q=0;q<s;q++)
            {
                if(ava[q]=='T')//The first available colour in the array will be assigned to to the node
                {
                    res[k]=q;
                    break;

                }
            }
            for(int r=0;r<s;r++)//Once again initializing the whole array 'T'
            {
                ava[r]='T';
            }

        }

        System.out.println("resulting array with colours: ");//Printing the colours asssigned to different nodes in an array
        for(int t=0;t<s;t++)
        {
            
            System.out.print(res[t]+" ");
        }

        System.out.println(" ");
        System.out.println();
        System.out.println("Timetable : ");
       int count=0;//Stores the number of subjects printed
       for(int t=0;t<s;t++)//To print all the sub whose exams can be conducted in the same slot
       {
           
            System.out.print("SLOT "+(t+1)+" : ");
            for(int h=0;h<s;h++)
            {
                if(res[h]==t)//printing all the subjects where node colour is 't'
                {
                    System.out.print(arr.get(h).subname+" ");
                    count++;
                    
                    
                }
            }
            System.out.println(" ");
            if(count==arr.size()) //When all the subjects are displayed it breaks out of the loop
            break;
        
       }
    }
    public static void main(String[] args)    //main
    {
        Timetable_project t=new Timetable_project();
        t.create();
        t.display();
        t.colour();
    }
}
