import java.util.*;
import java.io.*;

public class mywordladder {
  public static void main(String[] args){
    System.out.print("Please enter filename:");
    Scanner filename = new Scanner(System.in);
    String str1 = filename.nextLine();
    System.out.print("Please enter word1:");
    Scanner word1 = new Scanner(System.in);
    String str2 = filename.nextLine();
    System.out.print("Please enter word2:");
    Scanner word2 = new Scanner(System.in);
    String str3 = filename.nextLine();
    int result = find(str1,str2,str3);
  }
  public static int find(String str1, String str2, String str3) {
    /* write your code here */
    int ladder_size = 0;
    String file_name = null;                                   //the file name;
    file_name = str1;
    if (!file_name.equals("dictionary.txt") && !file_name.equals("Englishwords.txt") && !file_name.equals("smalldict1.txt") &&               // judge the input file-name if if valid;
      !file_name.equals("smalldict2.txt") && !file_name.equals("samlldict3.txt"))
      System.out.println("Unable to open the file . Try again");
    else {
      Vector file_word = new Vector();
      try {
        File readFile = new File(file_name);
        InputStream in = new BufferedInputStream(new FileInputStream(readFile));
        InputStreamReader ir = new InputStreamReader(in, "utf-8");
        BufferedReader br = new BufferedReader(ir);
        String str = "";
        while ((str = br.readLine()) != null) {
          file_word.add(str);
        }
      } catch (IOException e) {
      }
      String word1;
      word1 = str2;
      if (word1.equals("")) {
      } else {
        String word2;
        word2 = str3;
        if (word2.equals("")) {
        } else {
          int judge = 0;
          int word1_length = word1.length();
          int word2_length = word2.length();
          for (int i = 0; i < word1_length; i++) {
            char c = word1.charAt(i);
            if (!(c >= 'A' && c <= 'Z' || c >= 'a' && c <= 'z')) {
              System.out.println("invalid word1");
              judge = 1;
              break;
            }
          }
          for (int i = 0; i < word2_length; i++) {
            char c = word2.charAt(i);
            if (!(c >= 'A' && c <= 'Z' || c >= 'a' && c <= 'z')) {
              System.out.println("invalid word2");
              judge = 1;
              break;
            }
          }
          word1 = word1.toLowerCase();
          word2 = word2.toLowerCase();
          if (word1_length != word2_length) {                               // judge the two words if has the same length;
            System.out.println("The two words must be the same length.");
            judge = 1;
          } else if (!file_word.contains(word1)) {                      // judge the two words if in dictionary;
            System.out.println("The two words must be found in the dictionary.");                                    //
            judge = 1;                                                       //
          }                                                                    //
          else if (!file_word.contains(word2)) {                 //
            System.out.println("The two words must be found in the dictionary.");                                    //
            judge = 1;                                                       //
          } else if (word1.equals(word2)) {
            System.out.println("The two words must be different.");
            judge = 1;
          }
          if (judge != 0) {
            judge = 0;
          } else {
            int have_or_not = 0;
            Stack<Vector> ladder = new Stack<Vector>();
            Vector word1_vec = new Vector();
            word1_vec.add(word1);
            ladder.add(word1_vec);
            Vector same_len = new Vector();                                 //seat the element that length is same as the input word;
            int file_length = file_word.size();
            for (int i = 0; i < file_length; i++) {
              String now_word = (String) file_word.get(i);
              int now_word_length = now_word.length();
              if (now_word_length == word1_length) {
                same_len.add(now_word);
              }
            }
            Vector tem_seat = new Vector();              //the first time to find all the words which  only has a
            // word is different front the word1;
            int same_size = same_len.size();
            for (int i = 0; i < same_size; i++) {
              String str = (String) same_len.get(i);
              if (equal(word1, str)) {
                tem_seat.add(str);
              }
            }
            if (tem_seat.contains(word2)) {
              System.out.print("A ladder from ");
              System.out.print(word2);
              System.out.print(" back to ");
              System.out.print(word1);
              System.out.println(" : ");
              System.out.print(word2);
              System.out.print(" ");
              System.out.println(word1);
            }
          /*set<string>copy_same = same_len;
          for (it = copy_same.begin(); it != copy_same.end(); ++it) {              //
            string str = *it;
            if (equal(word2, str)) {                                           //
              tem_seat.insert(str);
            }
          }                                                                   //
          set<string>::iterator its;
          for (its = tem_seat.begin(); its != tem_seat.end(); ++its) {
            words = words + *its + " ";
          }
          if (tem_seat.contains(word2)) {
            cout << "A ladder from " << word2 << " back to " << word1 << ":" << endl;
            cout << word2 << " " << word1 << endl;
            cout << endl;
          */
            else {
              ladder.push(tem_seat);
              String tem_word;
              Vector copy_same = same_len;
              while (true) {                                    // one by one word to find the words which is differrnt from them just
                Vector tem_seat1 = new Vector();                  // element
                tem_word = "";
                int tem_seat_len = tem_seat.size();
                for (int i = 0; i < tem_seat_len; i++) {
                  String tem_str = (String) tem_seat.get(i);
                  copy_same.remove(tem_str);
                  int copy_len = copy_same.size();
                  for (int j = 0; j < copy_len; j++) {
                    String copy_str = (String) copy_same.get(j);
                    if (equal(tem_str, copy_str)) {
                      tem_seat1.add(copy_str);
                    }
                  }
                }
              /*
              for (it = tem_seat.begin(); it != tem_seat.end(); ++it) {
                string str = *it;
                copy_same.erase(str);
                for (its = copy_same.begin(); its != copy_same.end(); ++its) {
                  string strs = *its;
                  if (equal(str, strs)) {
                    tem_seat1.insert(strs);
                  }
                }
              }
              */
                int tem_seat1_size = tem_seat1.size();
                if (tem_seat1_size == 0) {
                  System.out.print("No word ladder found from ");
                  System.out.print(word2);
                  System.out.print(" back to ");
                  System.out.println(word1);
                  have_or_not = 1;
                  break;
                } else {
                  ladder.push(tem_seat1);
                  if (tem_seat1.contains(word2)) {
                    break;
                  }
                  tem_seat = tem_seat1;
                }
              }
              if (have_or_not == 0) {
                Vector output = new Vector();                                  //store the the word which is needed;
                ladder.pop();
                String copy_word2 = word2;
                while (!ladder.empty()) {
                  Vector pos = new Vector();
                  pos.add(-1);
                  Vector sr = ladder.peek();
                  int sr_len = sr.size();
                  for (int i = 0; i < sr_len; i++) {
                    String last_word = (String) sr.get(i);
                    if (equal(copy_word2, last_word)) {
                      output.add(last_word);
                      copy_word2 = last_word;
                      break;
                    }
                  }
                /*
                int sr_length = sr.length();
                for (int i = 0; i < sr_length; i++) {
                  if (sr.charAt(i) == ' ') {
                    pos.add(i);
                  }
                }
                int pos_size = pos.size();
                for (int j = 0; j < pos_size - 1; ++j) {
                  if (equal(copy_word2, sr.substr(pos[j] + 1, pos[j + 1] - pos[0]))) {
                    output.push_back(sr.substr(pos[j] + 1, pos[j + 1] - pos[j]));
                    copy_word2 = sr.substr(pos[j] + 1, pos[j + 1] - pos[j]);
                    break;
                  }
                }
                */
                  ladder.pop();
                }
                System.out.println("A ladder from " + word2 + " back to " + word1 + " : ");
                System.out.print(word2 + " ");
             /*  cout << "A ladder from " << word2 << " back to " << word1 << ":" << endl;
                 cout << word2 << " ";       */
                int output_size = output.size();
                for (int i = 0; i < output_size - 1; i++) {
                  String out_word = (String) output.get(i);
                  System.out.print(out_word + " ");
                }
             /* for (int i = output_size - 1; i >= 0; i--) {
                cout << output[i];
              }
              cout << word1 << endl;
              cout << endl;
              int final = clock();  */
                System.out.println(word1);
                ladder_size = output_size + 1;
              }
            }
          }
        }
      }
    }
    System.out.println("Have a nice day!");
    return ladder_size;
  }

  public static boolean equal(String s1, String s2) {
    int number = 0;
    int s1_length = s1.length();
    for (int i = 0; i < s1_length; ++i) {
      if (s1.charAt(i) != s2.charAt(i)) {
        number += 1;
      }
    }
    return (number == 1);
  }
}

