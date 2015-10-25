package lista_2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Main 
{

    public static void clearCheckTab(boolean Tab[])
    {
        /*for (int i=0;i<65;i++)
            Tab[i]=false;
        for (int i=91;i<97;i++)
            Tab[i]=false;
        for (int i=123;i<256;i++)
            Tab[i]=false;*/
        for (int i=0;i<256;i++)
                Tab[i]=false;
        for (int i=48;i<58;i++)
            Tab[i]=true;
        for (int i=65;i<91;i++)
            Tab[i]=true;
        for (int i=97;i<123;i++)
            Tab[i]=true;
        Tab[32]=true;
        Tab[33]=true;
        //Tab[34]=true;
        Tab[44]=true;
        //Tab[45]=true;
        Tab[46]=true;
        //Tab[39]=true;
        Tab[63]=true;
    }
    
    
    public static void main(String[] args) 
    {
        int uC=21;//useCiphers number
        String ciphers[] = new String[uC];
        String zad2="";
        //String key="",key1="",key2="";
        char key1[]=new char[38];
        char key2[]=new char[38];
        for (int p=0; p<38;p++)
        {
            key1[p]=0;
            key2[p]=0;
        }
        boolean keyPossible[]=new boolean[256];
        boolean keyTemp[]=new boolean[256];//if c<20
        boolean keyCheck[]=new boolean[256];//possible A for index
        boolean xoredCheck[]=new boolean[256];// possible A from Ax+Bx=AB
        boolean xorable[]=new boolean[256];//if in range to look for matches
        for (int p=0;p<256;p++)
        {
            xoredCheck[p]=false;
            keyTemp[p]=false;
            keyPossible[p]=true;
        }
        //clearCheckTab(keyCheck);
        clearCheckTab(keyCheck);
        //clearCheckTab(xoredCheck);
        clearCheckTab(xorable);
        
        /*char keyCheck[]=new char[256];
        String ciphers[] = new String[20];
        int index=0;
        for (char i='\u0000';i<256;i++,index++)
        {
            //System.out.print(i);
            //System.out.println(" <-");
            keyCheck[index]=i;
        }*/
        
        BufferedReader reader = null;
        
        try
        {
            char next;
            reader = new BufferedReader(new FileReader("C:\\Users\\nam\\Desktop\\ciphers.txt"));
            int counter=0,cipherNo=0;
            char value=0;
            while (cipherNo<=uC)
            {
                next=(char)reader.read();
                //System.out.println(next+" "+cipherNo);
                //counter++;
                switch(next)
                {
                    case '\n':
                    {
                        if (counter!=8)
                            System.out.println("Error: invalid number of bits read at '\n' "+counter);
                        //System.out.println("case: \n");
                        cipherNo++;
                        counter=0;
                        if (value<0||value>255)
                            System.out.println("Error: invalid value of byte read at '\n' "+(int)value);
                        value=0;
                        break;
                    }
                    case ' ':
                    {
                        if (counter!=8)
                            System.out.println("Error: invalid number of bits read at ' ' "+counter);
                        //System.out.println((char)value+" "+"<- tutttaaaaj "+(int)value+" "+Integer.toBinaryString((int)value)+" "+Integer.toBinaryString(value));
                        if (cipherNo<uC)
                        {
                        if (ciphers[cipherNo]==null)
                        {
                            //System.out.print("jebudu "+(char)value+" int "+(int)value);
                            ciphers[cipherNo]=""+(char)value;
                            System.out.print(" - "+ciphers[cipherNo]); 
                            System.out.println(" - "+ciphers[cipherNo].charAt(0)); 
                        }
                        else
                            ciphers[cipherNo]+=(char)value;
                        }
                        else
                        {
                           zad2+=(char)value;
                        }
                        if (value<0||value>255)
                            System.out.println("Error: invalid value of byte read at ' ' "+(int)value);
                        value=0;
                        counter=0;
                        break;
                    }
                    case '1':
                    {
                        //System.out.print("Old="+Integer.toBinaryString(value)+" + 1=");
                        value=(char)(value*2+1);
                        //System.out.println(Integer.toBinaryString(value)+" int="+(int)value+" char="+value);
                        counter++;
                        break;
                    }
                    case '0':
                    {
                        //System.out.print("Old="+Integer.toBinaryString(value)+" + 0=");
                        value=(char)((int)value*2);
                        //System.out.println(Integer.toBinaryString(value)+" int="+(int)value+" char="+value);
                        counter++;
                        break;
                    }
                    default:
                    {
                        //System.out.println("Error: case default");
                        break;
                    }
                }
            }
            for (int g=0;g<uC;g++)
            {
                System.out.println(ciphers[g]+" "+g);
            }
            reader.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        int flag=0,k,j;
        for (int i=0;i<ciphers[0].length();i++)
        {
            for (k=0;k<uC;k++)
            {
                
            for (j=0;j<uC;j++)
            {
                if (j!=k&&ciphers[k].length()>i&&ciphers[j].length()>i)
                {
                //char xored=(char)xorIt(ciphers[k].charAt(i),ciphers[j].charAt(i));
                    char xored=(char)(ciphers[k].charAt(i)^ciphers[j].charAt(i));
                //System.out.println("Xored "+ciphers[k].charAt(i)+" with "+ciphers[j].charAt(i)+" resulting "+xored);
                   // if (i==18)
                   // {
                   //     System.out.println('\n'+Integer.toBinaryString(ciphers[k].charAt(i))+" - "+(int)ciphers[k].charAt(i)+"<-k[i] j[i]-> "+(int)ciphers[j].charAt(i)+" - "+Integer.toBinaryString(ciphers[j].charAt(i)));
                   //     System.out.println((int)xored+" - "+Integer.toBinaryString(xored)+'\n');
                   // }
                    clearCheckTab(xorable);
                    if (i==0)
                    {
                        xorable[34]=true;xorable[39]=true;
                        keyCheck[34]=true;keyCheck[39]=true;}
                    if (i==18)
                    {
                        xorable[45]=true;
                        keyCheck[45]=true;}
                for (int a=32;a<256;a++)
                {
                    if (xorable[a]==true)
                    {
                        for (int b=32;b<256;b++)
                        {
                            if (xorable[b]==true&&a!=b)
                            {
                                //char xor=(char)xorIt((char)a,(char)b);
                                char xor=(char)(a^b);
                                    //if(xor>=0&&xor<256)
                                    if ((xor==xored)&&(int)xor!=0)
                                    {
                                        xoredCheck[a]=true;
                                        if (i==1)
                                        {
                                           // System.out.println(" * "+a+" "+"xor"+b+" = "+xored);
                                        }
                                        flag=1;
                                    }
                            }
                        }
                    }
                }
            
            //clearCheckTab(keyCheck);
            if (flag==1)//&&xored>32&&xored<123)
            {
            for (int p=32;p<256;p++)
            {
                if (xoredCheck[p]==false&&keyCheck[p]==true)
                {
                    keyCheck[p]=false;
                    //System.out.println("False @ "+p);
                }
            }
            flag=0;
            }
          //  for (int z=0;z<ciphers[0].length();z++)
                //System.out.print(ciphers[j].charAt(z));
           // System.out.println();
           // if (i==18)
              //  System.out.println("xoredCheck\n");
            for (int p=32;p<256;p++)
            {
               // if (i==18)
                 //   if(xoredCheck[p])
                  //      System.out.print(" - "+p);
                xoredCheck[p]=false;
            }
          //  if (i==18)
             //   System.out.println();
            
            
            
            
            }
            }
            int c=0;
           // if (i==18)
              //  System.out.println("keyCheck\n");
            for (int s=0;s<256;s++)
            {
                //if (i==18)
                 //   if(keyCheck[s])
                  //      System.out.print(" - "+s);
                if (keyCheck[s])
                    c++;
            }
           // if (i==18)
            //    System.out.println();
            //System.out.println(c+" dla "+i+" k="+k+" j="+j);
            /*if (c==1)
            {
                 int s=0;
                 for (s=32;s<123;s++)
                     if (keyCheck[s])
                         break;
                 key=key+(char)(ciphers[k].charAt(i)^s);
                 break;
            } */
            if (c<5)
            {
                 /*int s=0;
                 for (s=32;s<123;s++)
                     if (keyCheck[s])
                         break;
                 key1[i]=(char)(ciphers[k].charAt(i)^s);
                 for (s=s+1;s<123;s++)
                     if (keyCheck[s])
                         break;
                 key2[i]=(char)(ciphers[k].charAt(i)^s);
                 break;*/
                char temp;
                        Byte nowy2;
                                byte nowy;
                for(int s=32;s<256;s++)
                {
                    if (keyCheck[s])
                    {
                        //temp=(char)xorIt(ciphers[k].charAt(i),(char)s);
                        temp=(char)(ciphers[k].charAt(i)^s);
                        nowy=(byte)temp;
                        nowy2=nowy;
                        //System.out.println((char)nowy2.intValue()+" - "+nowy2.intValue()+" pierdole "+nowy2.shortValue()+" elo elo "+Integer.toString((int)nowy,2));
                        //System.out.println("TEMP = "+temp+" "+(int)temp);
                        if (temp>=0&&temp<256)
                        {
                            //System.out.println(" = "+(char)(temp));
                            keyTemp[temp]=true;
                        }
                    }
                }
                for (int s=0;s<256;s++)
                {
                    if (keyTemp[s]==false&&keyPossible[s]==true)
                        keyPossible[s]=false;
                }
                for (int s=0;s<256;s++)
                    keyTemp[s]=false;
                
            }
            clearCheckTab(keyCheck);
            
            
            }
            int czyklucz=0;
            for (int s=0;s<256;s++)
            {
                if (keyPossible[s])
                    czyklucz++;
                //keyPossible[s]=true;
            }
            System.out.println("Key possibilities "+czyklucz+" for "+i+'\n');
            if (czyklucz==1)
            {
                for (int s=0;s<256;s++)
                    if (keyPossible[s])
                    {
                        key1[i]=(char)s;
                        key2[i]=(char)s;
                    }
                //System.out.println("!!!");
            }
            else if (czyklucz==2)
            {
                int f=0;
                for (int s=0;s<256;s++)
                    if (f!=0&&keyPossible[s])
                        key2[i]=(char)s;
                    else if(keyPossible[s])
                    {
                        key1[i]=(char)s;
                        f=1;
                    }
            }
            for (int s=0; s<256;s++)
                keyPossible[s]=true;
            
        }
        //System.out.println(key);
        System.out.println(key1);
        System.out.println(key2);
        System.out.println("Start");
        for (int p=0;ciphers[0].length()>p;p++)
            System.out.print((char)(ciphers[0].charAt(p)^key1[p]));
        System.out.println();
        for (int p=0;ciphers[0].length()>p;p++)
            System.out.print((char)(ciphers[0].charAt(p)^key2[p]));
        System.out.println('\n');
        for (int i=0;i<uC;i++)
        {
        for (int p=0;ciphers[0].length()>p;p++)
            System.out.print((char)(ciphers[i].charAt(p)^key1[p]));
        System.out.println();
        for (int p=0;ciphers[0].length()>p;p++)
            System.out.print((char)(ciphers[i].charAt(p)^key2[p]));
        System.out.println();
        }
        char eł='ł';
        System.out.println((int)eł+"\n\nZad2\n");
        System.out.println(zad2);
        
        
        String z2Key="8a7a2e12";
        /*for (int p=0;p<zad2.length();p++)
        {
            for (int s=p;s<z2Key.length();s++)
                System.out.print((char)(zad2.charAt(s)^z2Key.charAt(s)));
            System.out.println("\n+next+\n");
            //z2Key=' '+z2Key;
        }*/
        System.out.println("zad2.l = "+zad2.length());
        System.out.println("z2Key.l = "+z2Key.length());
        for (int p=zad2.length()-z2Key.length();p<zad2.length();p++)
        {
            //System.out.println("p = "+p);
            System.out.print((char)(zad2.charAt(p)^z2Key.charAt(p-58)));
        }
        /*int counter=7;
        byte value1=0;
        System.out.println('\n');
        System.out.println(value1);
        value1=(byte)(value1|(1<<counter));
        System.out.println((char)value1+" aloha "+value1+"\n");
        value1=0;
        for (int i=0;i<8;i++)
        {
            value1=(byte)(value1|1<<i);
            System.out.println(value1);
            value1=0;
        }*/
        //char q=12,w=8,e=2;
        //System.out.println((int)xorIt(q,w)+" 12^8");
        //System.out.println((int)(q^w)+" 12^8");
        //System.out.println((int)xorIt(q,e)+" 12^2");
        //System.out.println((int)(q^e)+" 12^2");
        //System.out.println((int)xorIt(e,w)+" 2^8");
        //System.out.println(((q^w)^(q^e))+" up");
    }
    
}
