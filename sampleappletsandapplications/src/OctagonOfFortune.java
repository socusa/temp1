
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;

public class OctagonOfFortune extends JApplet implements ActionListener {
   private Octagon octagon;
   private Player[] players;
   private Player currentPlayer;
   private Timer timer;
   private JButton start;
   private JButton spin;
   private JButton buyvowel;
   private JButton solve;
   private Container container;
   private String[] words;
   private char[][] characters;
   private JButton[][] buttons;
   private JButton[] alphabet;
   private int currentAmount;
   private boolean bankrupt;
   private boolean[] used;
   private boolean sentinel;
   private boolean buyVowelSelected;
   private boolean solveSelected;
   private boolean gameOver;
   private int numberOfLettersLeft;
   private Message message;
   private SolvePanel solvePanel;
   private boolean[][] revealed;
   private Choice letters;
   private MediaTracker tracker;
   private AudioClip applause;
   private AudioClip boo;

   private JButton createJButton(String label,
                                 int width,
                                 int height,
                                 int x,
                                 int y) {
// This method is used to create a JButton without left and right margins.

      JButton button = new JButton(label);
      button.setSize(width,height);
      button.setLocation(x,y);

      Insets insets = button.getInsets();
      insets.left = 0;
      insets.right = 0;

      button.setMargin(insets);

      return(button);
   }

   private JTextField createJTextField(int size,
                                       int width,
                                       int height,
                                       int x,
                                       int y) {
// This method is used to create a JTextField.

      JTextField field = new JTextField(size);
      field.setSize(width,height);
      field.setLocation(x,y);

      return(field);
   }

   public String[] getPhrases() {
// This method is used to create an array of phrases.

      String[] temp = null;

      temp = new String[210];

      temp[0] = "Text has been lost in a cmdtool transcript because the maximum edit log size has been exceeded";
      temp[1] = "A continuous function on a closed and bounded interval attains a maximum and minimum on that interval.";
      temp[2] = "When two parallel lines are cut by a transversal, corresponding angles are congruent.";
      temp[3] = "Treating a superclass object as a subclass object can cause errors.";
      temp[4] = "Assigning an object of a superclass to a subclass reference (without a cast) is a syntax error.";
      temp[5] = "A class declared final cannot be extended and every method is implicitly final.";
      temp[6] = "Polymorphism as implemented with dynamic method binding is efficient.";
      temp[7] = "When an anonymous inner class implements an interface, the class must define every method in the interface.";
      temp[8] = "An inner class can be declared static.";
      temp[9] = "The outer class is responsible for creating objects of its non-static inner classes.";
      temp[10] = "An anonymous inner class can implement an interface or extend a class.";
      temp[11] = "Compiling a class that contains inner classes results in a seperate .class file for every class.";
      temp[12] = "The event generated when the user clicks the window's close box is a window closing event.";
      temp[13] = "Inner classes are defined inside the scope of other classes.";
      temp[14] = "Inner class definitions are used mainly in event handling.";
      temp[15] = "When a class implements an interface, the same \"is a\" relationship provided by inheritance applies.";
      temp[16] = "A superclass exists in an hierarchical relationship with its subclasses.";
      temp[17] = "An object of a subclass can be treated as an object of its corresponding superclass, but the reverse is not true.";
      temp[18] = "My life is not a paragraph and death I think is no parentheses. -\"Dave\"";
      temp[19] = "No one travels so high as he who knows not where he is going. -Oliver Cromwell";
      temp[20] = "Write as if you are dying -Annie Dillard";
      temp[21] = "Genius is one percent inspiration, and ninety-nine percent perspiration. -Thomas Edison";
      temp[22] = "Great spirits have always encountered violent opposition from mediocre minds. -Albert Einstein";
      temp[23] = "1. Out of clutter, find simplicity 2. From discord, find harmony 3. In the middle of difficulty lies opportunity ,(Three rules of work) -Albert Einstein";
      temp[24] = "Everything should be made as simple as possible, but not simpler. -Albert Einstein";
      temp[25] = "The creation of a thousand forests is in one acorn. -Ralph Waldo Emerson";
      temp[26] = "Whether you believe you can, or whether you believe you can't, you're absolutely right. -Henry Ford";
      temp[27] = "The more you think, the more time you have. -Henry Ford";
      temp[28] = "Inventions have long since reached their limit, and I see no hope for further development. Julius Sextus Frontinus";
      temp[29] = "Persistance is the hard work that you do after you are tired of doing the hard work you already did -Newt Gingrich";
      temp[30] = "What you can do,or dream you can, begin it; boldness has genius, power and magic in it. -Goethe";
      temp[31] = "To live is to have problems and to solve problems is to grow intellectually -J. P. Guilford";
      temp[32] = "It is good to have an end to journey toward; but it is the journey that matters, in the end -Ursula K. Le Guin";
      temp[33] = "You lose it if you talk about it. -Ernest Hemingway";
      temp[34] = "One cannot step twice into the same river. -Heraclitus";
      temp[35] = "What is originality? Undetected plagiarism. -Dean William R. Inge";
      temp[36] = "In the dim background of mind we know what we ought to be doing but somehow we cannot start. -William James";
      temp[37] = "Genius, in truth, means little more than the faculty of perceiving in an unhabitual way. -William James";
      temp[38] = "You cannot mandate productivity, you must provide the tools to let people become their best. -Steve Jobs";
      temp[39] = "A person needs a little madness, or else they never dare cut the rope and be free. -Nikos Kazantzakis";
      temp[40] = "The map is not the territory -Alfred Korzbyski";
      temp[41] = "An essential aspect of creativity is not being afraid to fail. -Dr. Edwin Land";
      temp[42] = "I used to think anyone doing anything weird was weird. Now I know that it is the people that call others weird that are weird. -Paul McCartney";
      temp[43] = "The obvious is always least understood -Prince Metternich";
      temp[44] = "To be a man of knowledge one needs to be light and fluid -Yaqui Mystic";
      temp[45] = "If I have seen further it is by standing on the shoulders of giants. -Isaac Newton";
      temp[46] = "The only joy in the world is to begin. -Cesare Pavese";
      temp[47] = "Computers are useless. They can only give you answers. -Pablo Picasso";
      temp[48] = "I do not seek. I find. -Pablo Picasso";
      temp[49] = "Teachers open the door, but you must enter by yourself. -Chinese Proverb";
      temp[50] = "Inspiration could be called inhaling the memory of an act never experienced. -Ned Rorem";
      temp[51] = "Be master of mind rather than mastered by mind. -Zen saying";
      temp[52] = "\"..in the sun i'll feel as one...\" -Kurt Cobain";
      temp[53] = "\"The Art of Peace is the religion that is not a religion; it perfects and completes all religions. -Morihei Ueshiba";
      temp[54] = "\"Ultimately, you must forget about technique. The further you progress, the fewer teachings there are. The Great Path is really No Path. -Morihei Ueshiba";
      temp[55] = "\"Even the most powerful human being has a limited sphere of strength. Draw him outside of that sphere and into your own, and his strength will dissipate. -Morihei Ueshiba";
      temp[56] = "\"Your spirit is the true shield. -Morihei Ueshiba";
      temp[57] = "\"The Art of Peace is to fulfill that which is lacking.\" -Morihei Ueshiba";
      temp[58] = "\"Be grateful even for hardship, setbacks, and bad people. Dealing with such obstacles is an essential part of training in the Art of Peace.\" -Morihei Ueshiba";
      temp[59] = "\"Do not look upon this world with fear and loathing. Bravely face whatever the gods offer.\" -Morihei Ueshiba";
      temp[60] = "\"When an opponent comes forward, move in and greet him; if he wants to pull back, send him on his way.\" -Morihei Ueshiba";
      temp[61] = "\"The techniques of the Art of Peace are neither fast nor slow, nor are they inside or outside. They transcend time and space.\" -Morihei Ueshiba";
      temp[62] = "\"A good stance and posture reflect a proper state of mind.\" -Morihei Ueshiba";
      temp[63] = "\"To injure an opponent is to injure yourself. To control aggression without inflicting injury is the Art of Peace.\" -Morihei Ueshiba";
      temp[64] = "\"The purpose of training is to tighten up the slack, toughen the body, and polish the spirit.\" -Morihei Ueshiba";
      temp[65] = "\"Loyalty and devotion lead to bravery. Bravery leads to the spirit of self-sacrifice. The spirit of self-sacrifice creates trust in the power of love.\" -Morihei Ueshiba";
      temp[66] = "\"The Art of Peace is based on Four Great Virtues: Bravery, Wisdom, Love, and Friendship, symbolized by Fire, Heaven, Earth, and Water.\" -Morihei Ueshiba";
      temp[67] = "\"When life is victorious, there is birth; when it is thwarted, there is death. A warrior is always engaged in a life-and-death struggle for Peace.\" -Morihei Ueshiba";
      temp[68] = "\"Silence is Foo.\" -Woody Woodpecker";
      temp[69] = "\"Random quotes can be most effecatious.\" -Anon";
      temp[70] = "\"To thine own self be true.\" -William Shakespaere";
      temp[71] = "\"...we are free at last!\" -Martin Luther King";
      temp[72] = "\"The truth shall set you free..\"";
      temp[73] = "\"I have not lost my mind - it's backed up on disk somewhere.\" -Anon";
      temp[74] = "\"Hegel was right when he said that we learn from history that man can never learn anything from history.\" -George Bernard Shaw";
      temp[75] = "\"Communism doesn't work because people like to own stuff.\" -Frank Zappa";
      temp[76] = "\"In the fight between you and the world, back the world.\" -Frank Zappa";
      temp[77] = "\"To ask the hard question is simple. -W. H. Auden";
      temp[78] = "\"I'm not young enough to know everything.\" -J. M. Barrie";
      temp[79] = "\"Do not seek to follow in the footsteps of the men of old; seek what they sought.\" -Bash";
      temp[80] = "\"An idea can turn to dust or magic, depending on the talent that rubs against it.\" -Anon";
      temp[81] = "\"If the doors of perception were cleansed everything would appear to man as it is, infinite.\" -William Blake";
      temp[82] = "\"I must create a system or be enslaved by another man's. I will not reason and compare; My business is to create.\" -William Blake";
      temp[83] = "\"Life is:'trying things to see if they work'\" -Ray Bradbury";
      temp[84] = "A hunch is creativity trying to tell you something. -Frank Capra";
      temp[85] = "Whenever man comes up with a better mousetrap, nature immediately comes up with a better mouse. -James Carswell";
      temp[86] = "\"One sees great things from the valley, only small things from the peak.\" -G. K. Chesterton";
      temp[87] = "\"When the way comes to an end, then change - having changed, you pass through.\" -I Ching";
      temp[88] = "\"No idea is so outlandish that it should not be considered with a searching but at the same time steady eye.\" -Winston Churchill";
      temp[89] = "Music is the art of thinking with sounds. -Jules Combarie";
      temp[90] = "OK, for now this is the end of the line. As soon as we get more quotes fromreaders though, this is where they will go. SO WHAT ARE YOU WAITING FOR!? Get to The Box";
      temp[91] = "Freedom's just another word for nothing left to lose, and you ain't got nothing unless it's love. -Inspired by Janis Joplin lyric";
      temp[92] = "\"For there is nothing either good or bad, thinking makes it so.\" --William Shakespeare";
      temp[93] = "No person can be a great leader unless he takes genuine joy in the successes of those under him. - W. A. Nance";
      temp[94] = "Reality is the result of your dreams.-CER";
      temp[95] = "To strive, to seek, to find, but not to yield-Plato";
      temp[96] = "We are not who we think we are. We are not who others think we are. We are who we think others think we are.";
      temp[97] = "The courage we desire and prize is not the courage to die decently, but to live manfully. -Thomas Carlyle";
      temp[98] = "It's not what you say, but how you say it- Aaron Lima";
      temp[99] = "Ability wins us the esteem of the true me, luck that of the people. -La Rochefoucauld";
      temp[100] = "Consider well what your strength is equal to, and what exceeds your ability. -Horace";
      temp[101] = "An able man shows his spirit by gentle words and resolute actions; he is neither hot nor timid. -Chesterfield";
      temp[102] = "The bitter clamour of two eager tongues. -Shakespeare";
      temp[103] = "No reckoning make, but sent to my account With all my imperfections on my head. -Shakespeare";
      temp[104] = "All the world's a stage. -Shakespeare";
      temp[105] = "Adversity borrows its sharpest sting from our impatience. -Bishop Horne";
      temp[106] = "Adversity has the effect of eliciting talents, which in prosperous circumstances would have lain dormant. -Horace";
      temp[107] = "The good are better made by ill, As odors crush'd are sweeter still. -Rogers";
      temp[108] = "We as advice, be we mean approbation. -Colton";
      temp[109] = "Let no man presume to give advice to others that has not first given good counsel to himself. -Seneca";
      temp[110] = "A nickel isn't worth a dime anymore.";
      temp[111] = "Referring to the bad sun conditions in left field at the stadium. \"it gets late out there early\"";
      temp[112] = "During his movie review television show referring to actress Glen Close. Yogi called her \"Glen Cove\" (a village on Long Island)";
      temp[113] = "It's tough to make predictions, especially about the future.";
      temp[114] = "Nobody goes there anymore its too crowded";
      temp[115] = "If the fans don't come out to the ball park, you can't stop them.";
      temp[116] = "I didn't say the things I said.";
      temp[117] = "Its deja vu all over again.";
      temp[118] = "It ain't over until its over.";
      temp[119] = "Ninety percent of baseball is mental, the other half is physical.";
      temp[120] = "Yogi was interview after a game, as compensation he recieved a check \"Pay to the order of BEARER.\" He said, \"I've known this guy for so long, he can't spell my name right.\"";
      temp[121] = "When asked what time it was, Yogi said \"Do you mean now?\"";
      temp[122] = "I take a two hour nap between 1 and 4 in the afternoon.";
      temp[123] = "90% of the putts that are short don't go in.";
      temp[124] = "You can see a lot by observing.";
      temp[125] = "You have to give 100 percent in the first half of the game. If that isn't enough, in the second half, you have to give what is left.";
      temp[126] = "When asked \"What would you do if you found $1 million?\" Yogi responded, \"If the guy was poor, I'd give it back.\"";
      temp[127] = "Yogi saw three of his players in the locker room wearing Cone Head hats, Yogi said, \"those guys make a pair.\"";
      temp[128] = "If you come to a fork in the road, take it.";
      temp[129] = "I made a wrong mistake.";
      temp[130] = "Yogi met George Bush during an election campaign. Bush said Texas was important. Yogi said \"Texas has a lot of electrical votes.\"";
      temp[131] = "Yogi order a pizza, the waitress asked how many pieces do you want your pie cut? Yogi responded, \"4, I don't think I could eat 8.\"";
      temp[132] = "During a game of 20 Questions Yogi asked \"Is he living?\" and next \"Is he living now?\"";
      temp[133] = "After seeing the opera Tosca, Yogi remarked, \"I really liked it, even the music was nice.\"";
      temp[134] = "Phil Rizzuto - \"Hey Yogi I think we're lost.\" - Yogi Berra - \"Ya, but we're making great time!\"";
      temp[135] = "One day a repairman came to fix Yogi's Venitian BLINDS. Larry (Yogi's son) said \"the man is here for the venetian BLINDS.\" Yogi said give him $5.00.";
      temp[136] = "I couldn't tell if the streaker was a man or a woman because it had a bag on it's head.";
      temp[137] = "He is a big clog in their machine.";
      temp[138] = "Yogi Berra on seeing a Steve McQueen movie: - \"He must have made that before he died.\"";
      temp[139] = "If you can't imitate him, don't copy him.";
      temp[140] = "Mrs. Lindsay (the mayor's wife) - \"You certainly look cool.\" - Yogi Berra - \"Thanks, you don't look so hot yourself.\"";
      temp[141] = "I knew I was going to take the wrong train, so I left early.";
      temp[142] = "Interviewer - \"Why, you're a fatalist !\" - Yogi Berra - \"You mean I save postage stamps? Not me.\"";
      temp[143] = "You got to be very careful if you don't know where you're going, because you might not get there.";
      temp[144] = "Slump? I ain't in no slump. I just ain't hittin.";
      temp[145] = "It's pretty far, but it doesn't seem like it.";
      temp[146] = "It was impossible to get a conversation going, everybody was talking too much.";
      temp[147] = "Never answer an anonymous letter.";
      temp[148] = "At Yogi Berra day in St Louis 1947 \"I want to thank you for making this day necessary.\"";
      temp[149] = "If the world were perfect, it wouldn't be.";
      temp[150] = "Yogi on the 1969 NY Mets.....\"overwhelming underdogs.\"";
      temp[151] = "On why NY lost the 1960 series to Pittsburgh \" We made to many wrong mistakes.";
      temp[152] = "The future ain't what it used to be.";
      temp[153] = "During a baseball practice, \"Pair up in threes.\"";
      temp[154] = "Who has not be amazed to learn that the function y = e^x , like a phoenix rising again from its own ashes, is its own derivative?";
      temp[155] = "A work of morality, politics, criticism will be more elegant, other things being equal, if it is shaped by the hand of geometry.";
      temp[156] = "He is like the fox, who effaces his tracks in the sand with his tail.";
      temp[157] = "I recognize the lion by his paw.";
      temp[158] = "To isolate mathematics from the practical demands of the sciences is to invite the sterility of a cow shut away from the bulls.";
      temp[159] = "When writing about transcendental issues, be transcendentally clear.";
      temp[160] = "Nature to him was an open book, whose letters he could read without effort.";
      temp[161] = "Descartes commanded the future from his study more than Napoleon from the throne.";
      temp[162] = "He who understands Archimedes and Apollonius will admire less the achievements of the foremost men of later times.";
      temp[163] = "The calculus is the greatest aid we have to the application of physical truth in the broadest sense of the word.";
      temp[164] = "It is not once nor twice but times without number that the same ideas make their appearance in the world.";
      temp[165] = "A mathematician's reputation rests on the number of bad proofs he has given.";
      temp[166] = "One of the endlessly alluring aspects of mathematics is that its thorniest paradoxes have a way of blooming into beautiful theories.";
      temp[167] = "And perhaps, posterity will thank me for having shown it that the ancients did not know everything.";
      temp[168] = "One can measure the importance of a scientific work by the number of earlier publications rendered superfluous by it.";
      temp[169] = "Take from all things their number and all shall perish.";
      temp[170] = "Number theorists are like lotus-eaters -- having once tasted of this food they can never give it up.";
      temp[171] = "The imaginary number is a fine and wonderful recourse of the divine spirit, almost an amphibian between being and not being.";
      temp[172] = "...mathematical proofs, like diamonds, are hard and clear, and will be touched with nothing but strict reasoning. D. Burton, Elementary Number Theory, Boston: Allyn and Bacon 1980.";
      temp[173] = "Bridges would not be safer if only people who knew the proper definition of a real number were allowed to design them.";
      temp[174] = "Reason's last step is the recognition that there are an infinite number of things which are beyond it.";
      temp[175] = "The magic words are squeamish ossifrage";
      temp[176] = "Wherever groups disclosed themselves, or could be introduced, simplicity crystallized out of comparative chaos.";
      temp[177] = "Common sense is the collection of prejudices acquired by age eighteen.";
      temp[178] = "Nature laughs at the difficulties of integration.";
      temp[179] = "Algebra and money are essentially levelers; the first intellectually, the second effectively.";
      temp[180] = "As for everything else, so for a mathematical theory: beauty can be perceived but not explained.";
      temp[181] = "A theory has only the alternative of being right or wrong. A model has a third possibility: it may be right, but irrelevant.";
      temp[182] = "Since the mathematicians have invaded the theory of relativity, I do not understand it myself anymore.";
      temp[183] = "The truth of a theory is in your mind, not in your eyes.";
      temp[184] = "Theory attracts practice as the magnet attracts iron.";
      temp[185] = "It is clear that Economics, if it is to be a science at all, must be a mathematical science.";
      temp[186] = "Statistics: the mathematical theory of ignorance.";
      temp[187] = "He who loves practice without theory is like the sailor who boards ship without a rudder and compass and never knows where he may cast.";
      temp[188] = "Later generations will regard Mengenlehre (set theory) as a disease from which one has recovered.";
      temp[189] = "To think I have spent my life on absolute muck.";
      temp[190] = "Technical skill is mastery of complexity while creativity is mastery of simplicity.";
      temp[191] = "Experimentalists think that it is a mathematical theorem while the mathematicians believe it to be an experimental fact.";
      temp[192] = "If your new theorem can be stated with great simplicity, then there will exist a pathological exception.";
      temp[193] = "The greatest unsolved theorem in mathematics is why some people are better at it than others.";
      temp[194] = "To state a theorem and then to show examples of it is literally to teach backwards.";
      temp[195] = "Defendit numerus: There is safety in numbers.";
      temp[196] = "Like the crest of a peacock so is mathematics at the head of all knowledge.";
      temp[197] = "For the things of this world cannot be made known without a knowledge of mathematics.";
      temp[198] = "... what a wealth, what a grandeur of thought may spring from what slightbeginnings.";
      temp[199] = "Life is a school of probability.";
      temp[200] = "It is the perennial youthfulness of mathematics itself which marks it off with a disconcerting immortality from the other sciences.";
      temp[201] = "\"Obvious\" is the most dangerous word in mathematics.";
      temp[202] = "The longer mathematics lives the more abstract -- and therefore, possibly also the more practical -- it becomes.";
      temp[203] = "It is the merest truism, evident at once to unsophisticated observation, that mathematics is a human invention.";
      temp[204] = "Projective geometry is all geometry.";
      temp[205] = "Mathematics seems to endow one with something like a new sense.";
      temp[206] = "With me everything turns into mathematics.";
      temp[207] = "Logic, like whiskey, loses its beneficial effect when taken in too large quantities.";
      temp[208] = "Human life is proverbially uncertain; few things are more certain than the solvency of a life-insurance company.";
      temp[209] = "Do not worry about your difficulties in mathematics, I assure you that mine are greater.";

      for (int i=0;i<temp.length;i++)
         temp[i] = temp[i].toUpperCase();

      return(temp);
   }

   public int displayLetter(String letter) {
// This method searches through the phrase to find occurrences of letter.
// When it is found that letter is revealed, and the revealed array is
// adjusted. Also we count how many times the letter appears and return
// the count.

      int count = 0;

      for (int i=0;i<words.length;i++)
         for (int j=0;j<words[i].length();j++)
            if (letter.charAt(0) == words[i].charAt(j)) {
               buttons[i][j].setText(letter);
               revealed[i][j] = true;
               count++;
            }

      return(count);
   }
            
   public void displayWord(String word,int horizontalStart,int verticalPosition,int counter) {
// This method is used as an auxillary method to the displayPhrase method.
      for (int i=0;i<word.length();i++) {
         container.add(buttons[counter][i] = createJButton(String.valueOf(word.charAt(i)),20,20,horizontalStart+20*i,verticalPosition));
         if ((int)(word.charAt(i)) >= 65 && (int)(word.charAt(i)) <= 90)
            buttons[counter][i].setText("");
      }
   }

   public void displayPhrase() {
// In this method we obtain a random phrase, and create and fill the array
// of words, characters, buttons, and booleans. We then go through the
// word array and decide whether or not the next word can be displayed on
// the same line. If it can, then it is displayed. Otherwise the word is
// displayed on the next line.

      String[] phrases = getPhrases();

      String phrase = phrases[(int)(phrases.length*Math.random())];

      int verticalBase = 55;
      int horizontalBase = 10;

      int xwidth = 0;

      StringTokenizer tempST = new StringTokenizer(phrase);

      words = new String[tempST.countTokens()];
      characters = new char[tempST.countTokens()][20];
      buttons = new JButton[tempST.countTokens()][20];
      revealed = new boolean[tempST.countTokens()][20];

      numberOfLettersLeft = 0;

      int count = 0;

      for (int i=0;i<words.length;i++) {
         words[i] = tempST.nextToken();
         for (int j=0;j<words[i].length();j++) {
            characters[i][j] = words[i].charAt(j);
            revealed[i][j] = false;
            if ((int)characters[i][j] >= 65 &&
                (int)characters[i][j] <=90)
            numberOfLettersLeft++;
         }
         count += words[i].length();
      }

      int counter = 0;

      int lines = 0;

      while (counter < words.length) {
         int length = words[counter].length()*20;

         if (xwidth + length < getWidth()) {
            displayWord(words[counter],xwidth,verticalBase+20*lines,counter);
            xwidth += length+20;
         } else {
            xwidth = 0;
            lines++;
            displayWord(words[counter],xwidth,verticalBase+20*lines,counter);
            xwidth += length+20;
         }

         counter++;
      }
   }

   public int numberOfConsonantsLeft() {
// This method is used to decide how many consonants are unrevealed.

      int count = 0;

      for (int i=0;i<words.length;i++)
         for (int j=0;j<words[i].length();j++)
            if (!vowel(words[i].charAt(j)) &&
                buttons[i][j].getText().equals(""))
               count++;

      return(count);
   }

   private boolean vowel(char c) {
// This method is used to decide whether or not a character is a vowel.

      return(c == 'A' || c == 'E' ||
             c == 'I' || c == 'O' ||
             c == 'U');
   }
 
   public void createAlphabet() {
// This method creates the alphabet array and set the buttons initially
// invisible.

      used = new boolean[26];

      for (int i=65;i<=90;i++) {
         int horizontalAdjustment = 20*(i-65 - 13*(int)((i-65)/13));
         int verticalAdjustment = 20*((int)((i-65)/13));
         alphabet[i-65] = createJButton(String.valueOf((char)i),20,20,horizontalAdjustment,verticalAdjustment);         
         container.add(alphabet[i-65]);
         alphabet[i-65].setVisible(false);
         alphabet[i-65].addActionListener(this);
         used[i-65] = false;
      }
   }
            
   public void displayConsonants() {
// This method displays all of the unused consonants.

      for (int i=65;i<=90;i++) {
         if (!vowel((char)i) &&
             !used[i-65])
            alphabet[i-65].setVisible(true);
         else
            alphabet[i-65].setVisible(false);
      }
   }
            
   public void displayVowels() {
// This method displays all of the unused vowels.

      for (int i=65;i<=90;i++) {
         if (vowel((char)i) &&
             !used[i-65])
            alphabet[i-65].setVisible(true);
         else
            alphabet[i-65].setVisible(false);
      }
   }

   public void removeAllLetters() {
// This method makes the alphabet invisible.

      for (int i=65;i<=90;i++)
         alphabet[i-65].setVisible(false);
   }

   public void restoreUnrevealed() {
// This method is used to return the puzzle to the state it was in
// before a used clicked the solve button and selected an incorrect
// character.

      for (int i=0;i<words.length;i++)
         for (int j=0;j<words[i].length();j++)
            if (!revealed[i][j] &&
                (int)characters[i][j] >= 65 &&
                (int)characters[i][j] <= 90)
               buttons[i][j].setText("");
   }

   public Image[] getImages() {
// This method loads some images and chooses an array of three
// randomly and returns it.

      Image[] images = new Image[15];

      images[0] = getImage(getCodeBase(),"imagesoctagonoffortune/brittany.gif");
      images[1] = getImage(getCodeBase(),"imagesoctagonoffortune/christina.gif");
      images[2] = getImage(getCodeBase(),"imagesoctagonoffortune/courtney.gif");
      images[3] = getImage(getCodeBase(),"imagesoctagonoffortune/fran.gif");
      images[4] = getImage(getCodeBase(),"imagesoctagonoffortune/jennifer.jpg");
      images[5] = getImage(getCodeBase(),"imagesoctagonoffortune/julia1.gif");
      images[6] = getImage(getCodeBase(),"imagesoctagonoffortune/katey.gif");
      images[7] = getImage(getCodeBase(),"imagesoctagonoffortune/neve.gif");
      images[8] = getImage(getCodeBase(),"imagesoctagonoffortune/rosie.gif");
      images[9] = getImage(getCodeBase(),"imagesoctagonoffortune/sarah.gif");
      images[10] = getImage(getCodeBase(),"imagesoctagonoffortune/suzanne.gif");
      images[11] = getImage(getCodeBase(),"imagesoctagonoffortune/debbie.gif");
      images[12] = getImage(getCodeBase(),"imagesoctagonoffortune/gwyneth.jpg");
      images[13] = getImage(getCodeBase(),"imagesoctagonoffortune/julianne.jpg");
      images[14] = getImage(getCodeBase(),"imagesoctagonoffortune/alf.jpg");

      tracker = new MediaTracker(this);

      for (int i=0;i<15;i++)
         tracker.addImage(images[i],1);

      Message message = null;

      container.add(message = new Message("The images are now loading",getWidth(),50,0,250));

      System.out.println("Now loading ...");

      try {
         tracker.waitForAll();
      } catch (InterruptedException ie) {
      }

      System.out.println("Loaded");

      message.setVisible(false);

      message = null;

      for (int i=0;i<15;i++)
         images[i] = images[i].getScaledInstance(60,60,Image.SCALE_DEFAULT);

      int first = (int)(15*Math.random());

      int second = first;

      while (second == first)
         second = (int)(15*Math.random());

      int third = second;

      while (third == second ||
             third == first)
         third = (int)(15*Math.random());

      System.out.println(first + " " + second + " " + third);

      Image[] temp = new Image[3];

      temp[0] = images[first];
      temp[1] = images[second];
      temp[2] = images[third];

      return(temp);
   }

   public class Octagon extends JPanel implements Runnable {
      private Thread thread;
      private Color[] colors;
      private String[] values;
      private AudioClip beep;
      private JTextField status;
      private int current;
      private int numberOfTurns;
      private int counter;
      private AudioClip[] sounds;
     
      public Octagon(int width,
                     int height,
                     int x,
                     int y) {
         setSize(width,height);
         setLocation(x,y);

         colors = new Color[8];
         values = new String[8];
         sounds = new AudioClip[8];

         colors[0] = Color.black;
         values[0] = "Bankrupt";
         sounds[0] = getAudioClip(getCodeBase(),"soundsoctagonoffortune/bankrupt.au");
         colors[1] = Color.red;
         values[1] = "$100";
         sounds[1] = getAudioClip(getCodeBase(),"soundsoctagonoffortune/onehundreddollars.au");
         colors[2] = Color.green;
         values[2] = "$1000";
         sounds[2] = getAudioClip(getCodeBase(),"soundsoctagonoffortune/onethousanddollars.au");
         colors[3] = Color.yellow;
         values[3] = "$500";
         sounds[3] = getAudioClip(getCodeBase(),"soundsoctagonoffortune/fivehundreddollars.au");
         colors[4] = Color.blue;
         values[4] = "$5000";
         sounds[4] = getAudioClip(getCodeBase(),"soundsoctagonoffortune/fivethousanddollars.au");
         colors[5] = Color.gray;
         values[5] = "$2000";
         sounds[5] = getAudioClip(getCodeBase(),"soundsoctagonoffortune/twothousanddollars.au");
         colors[6] = Color.orange;
         values[6] = "$700";
         sounds[6] = getAudioClip(getCodeBase(),"soundsoctagonoffortune/sevenhundreddollars.au");
         colors[7] = Color.magenta;
         values[7] = "$800";
         sounds[7] = getAudioClip(getCodeBase(),"soundsoctagonoffortune/eighthundreddollars.au");
   
         setLayout(null);

         add(status = createJTextField(25,100,20,0,80));

         current = 0;

         counter = 0;

         beep = getAudioClip(getDocumentBase(),"soundsoctagonoffortune/quack.au");
      }

      public void start(int numberOfTurns) {
         if (thread != null)
            thread = null;
         thread = new Thread(this);
         this.numberOfTurns = numberOfTurns;
         counter = 0;
         thread.start();
      }

      public void draw(int radius,
                       int centerx,
                       int centery,
                       Graphics g) {
         final double angle1 = (3.1415926535/180)*22.5;
         final double angle2 = (3.1415926535/180)*67.5;

         int[] xpoints1 = {centerx,(int)(centerx+radius*Math.cos(angle2)),(int)(centerx+radius*Math.cos(angle1))};
         int[] ypoints1 = {centery,(int)(centery-radius*Math.sin(angle2)),(int)(centery-radius*Math.sin(angle1))};

         int[] xpoints2 = {centerx,(int)(centerx+radius*Math.cos(angle1)),(int)(centerx+radius*Math.cos(angle1))};
         int[] ypoints2 = {centery,(int)(centery-radius*Math.sin(angle1)),(int)(centery+radius*Math.sin(angle1))};

         int[] xpoints3 = {centerx,(int)(centerx+radius*Math.cos(angle1)),(int)(centerx+radius*Math.cos(angle2))};
         int[] ypoints3 = {centery,(int)(centery+radius*Math.sin(angle1)),(int)(centery+radius*Math.sin(angle2))};

         int[] xpoints4 = {centerx,(int)(centerx+radius*Math.cos(angle2)),(int)(centerx-radius*Math.cos(angle2))};
         int[] ypoints4 = {centery,(int)(centery+radius*Math.sin(angle2)),(int)(centery+radius*Math.sin(angle2))};

         int[] xpoints5 = {centerx,(int)(centerx-radius*Math.cos(angle2)),(int)(centerx-radius*Math.cos(angle1))};
         int[] ypoints5 = {centery,(int)(centery+radius*Math.sin(angle2)),(int)(centery+radius*Math.sin(angle1))};

         int[] xpoints6 = {centerx,(int)(centerx-radius*Math.cos(angle1)),(int)(centerx-radius*Math.cos(angle1))};
         int[] ypoints6 = {centery,(int)(centery+radius*Math.sin(angle1)),(int)(centery-radius*Math.sin(angle1))};

         int[] xpoints7 = {centerx,(int)(centerx-radius*Math.cos(angle1)),(int)(centerx-radius*Math.cos(angle2))};
         int[] ypoints7 = {centery,(int)(centery-radius*Math.sin(angle1)),(int)(centery-radius*Math.sin(angle2))};

         int[] xpoints8 = {centerx,(int)(centerx-radius*Math.cos(angle2)),(int)(centerx+radius*Math.cos(angle2))};
         int[] ypoints8 = {centery,(int)(centery-radius*Math.sin(angle2)),(int)(centery-radius*Math.sin(angle2))};

         g.setColor(colors[(current+0) % 8]);
         g.fillPolygon(xpoints1,ypoints1,3);
         g.setColor(colors[(current+1) % 8]);
         g.fillPolygon(xpoints2,ypoints2,3);
         g.setColor(colors[(current+2) % 8]);
         g.fillPolygon(xpoints3,ypoints3,3);
         g.setColor(colors[(current+3) % 8]);
         g.fillPolygon(xpoints4,ypoints4,3);
         g.setColor(colors[(current+4) % 8]);
         g.fillPolygon(xpoints5,ypoints5,3);
         g.setColor(colors[(current+5) % 8]);
         g.fillPolygon(xpoints6,ypoints6,3);
         g.setColor(colors[(current+6) % 8]);
         g.fillPolygon(xpoints7,ypoints7,3);
         g.setColor(colors[(current+7) % 8]);
         status.setText(values[(current+7) % 8]);
         g.fillPolygon(xpoints8,ypoints8,3);
      }

      public void paint(Graphics g) {
         g.clearRect(0,0,getWidth(),getHeight());

         beep.play();

         status.repaint();

         draw(30,50,40,g);
      }

      public void run() {
         while (counter < numberOfTurns) {
            try {
               Thread.sleep(500);
            } catch (InterruptedException ie) {
            }

            current++;
            counter++;

            repaint();
         }

         try {
            Thread.sleep(1000);
         } catch (InterruptedException ie) {
         }

         sounds[(current+7) % 8].play();

         if ((current+7) % 8 != 0) {
            currentAmount = new Integer(values[(current+7) % 8].substring(1)).intValue();
            sentinel = true;
         } else {
            currentPlayer.setScore(0);
            currentPlayer.unSetCurrent();
            currentPlayer = players[(currentPlayer.getNumber() + 1) % 3];
            currentPlayer.setCurrent();
            spin.setVisible(true);
            buyvowel.setVisible(true);
            solve.setVisible(true);
         }

         thread = null;
      }
   }

   public class Player extends JPanel {
      private Image image;
      private JLabel label;
      private JTextField name;
      private JTextField money;
      private boolean current;
      private int number;

      public Player(int width,
                    int height,
                    int x,
                    int y) {
         setSize(width,height);
         setLocation(x,y);

         setLayout(null);

         name = new JTextField();
         name.setSize(50,20);
         name.setLocation(4,75);
         name.setText("");

         money = new JTextField();
         money.setSize(70,20);
         money.setLocation(58,75);
         money.setText("$0");

         add(name);
         add(money);

         current = false;
      }

      public void setImage(Image image) {
         this.image = image;
      }

      public void setCurrent() {
         current = true;
         repaint();
      }
     
      public void unSetCurrent() {
         current = false;
         repaint();
      }

      public void setName(String name) {
         this.name.setText(name);
      }

      public String getName() {
         return(name.getText());
      }

      public void setScore(int score) {
         if (score < 0)
            money.setText("-$" + score);
         else
            money.setText("$" + score);
      }

      public void increaseScore(int amount) {
         int score = 0;

         if (money.getText().charAt(0) == '$') {
            score = new Integer(money.getText().substring(1)).intValue();
            int newScore = score+amount;
            if (newScore > 0)
               money.setText("$" + String.valueOf(Math.abs(newScore)));
            else
               money.setText("-$" + String.valueOf(Math.abs(newScore)));
         } else {
            score = -1*new Integer(money.getText().substring(2)).intValue();         
            int newScore = score+amount;
            if (newScore > 0)
               money.setText("$" + String.valueOf(Math.abs(newScore)));
            else
               money.setText("-$" + String.valueOf(Math.abs(newScore)));
         }
      }

      public void reduceScore(int amount) {
         int score = 0;

         if (money.getText().charAt(0) == '$') {
            score = new Integer(money.getText().substring(1)).intValue();
            int newScore = score-amount;
            if (newScore > 0)
               money.setText("$" + String.valueOf(Math.abs(newScore)));
            else
               money.setText("-$" + String.valueOf(Math.abs(newScore)));
         } else {
            score = -1*new Integer(money.getText().substring(2)).intValue();         
            int newScore = score-amount;
            if (newScore > 0)
               money.setText("$" + String.valueOf(Math.abs(newScore)));
            else
               money.setText("-$" + String.valueOf(Math.abs(newScore)));
         }
      }

      public void setNumber(int number) {
         this.number = number;
      }

      public int getNumber() {
         return(number);
      }

      public String toString() {
         return("Player " + number);
      }

      public void paint(Graphics g) {
         g.clearRect(0,0,getWidth(),getHeight());

         Color color = (current) ? Color.red : Color.black;

         g.setColor(color);

         g.drawLine(1,1,getWidth(),1);
         g.drawLine(getWidth()-1,1,getWidth()-1,getHeight()-1);
         g.drawLine(getWidth()-1,getHeight()-1,1,getHeight()-1);
         g.drawLine(1,getHeight(),1,1);

         g.setColor(Color.black);

         name.repaint();
         money.repaint();

         g.setFont(new Font("Serif",Font.PLAIN,12));

         g.drawString("Name:",4,72);

         if (image != null) {
            int width = image.getWidth(this);
            g.drawImage(image,getWidth()/2-width/2,2,this);
         }
      }

      public void redraw() {
         repaint();
      }
   }

   public class Timer extends JPanel implements Runnable {
      private Thread thread;
      private boolean keepGoing;
      private int baseTime;
      private int elapsedSeconds;
      private Image[] digits;
      private Image colon;

      public Timer(int width,
                   int height,
                   int x,
                   int y) {
         setSize(width,height);
         setLocation(x,y);

         setBackground(Color.white);

         digits = new Image[10];

         digits[0] = getImage(getCodeBase(),"digitsoctagonoffortune/zero.gif");
         digits[1] = getImage(getCodeBase(),"digitsoctagonoffortune/one.gif");
         digits[2] = getImage(getCodeBase(),"digitsoctagonoffortune/two.gif");
         digits[3] = getImage(getCodeBase(),"digitsoctagonoffortune/three.gif");
         digits[4] = getImage(getCodeBase(),"digitsoctagonoffortune/four.gif");
         digits[5] = getImage(getCodeBase(),"digitsoctagonoffortune/five.gif");
         digits[6] = getImage(getCodeBase(),"digitsoctagonoffortune/six.gif");
         digits[7] = getImage(getCodeBase(),"digitsoctagonoffortune/seven.gif");
         digits[8] = getImage(getCodeBase(),"digitsoctagonoffortune/eight.gif");
         digits[9] = getImage(getCodeBase(),"digitsoctagonoffortune/nine.gif");

         colon = getImage(getCodeBase(),"digitsoctagonoffortune/colon.gif");

         Calendar calendar = Calendar.getInstance();

         int hour = calendar.get(Calendar.HOUR_OF_DAY);
         int minute = calendar.get(Calendar.MINUTE);
         int second = calendar.get(Calendar.SECOND);

         baseTime = 3600*hour + 60*minute + second;

         if (thread != null)
            thread = null;
         thread = new Thread(this);
         keepGoing = true;
         thread.start();
      }

      public void start() {
         if (thread != null)
            thread = null;
         thread = new Thread(this);
         keepGoing = true;
         thread.start();
      }

      public void paint(Graphics g) {
         g.clearRect(0,0,getWidth(),getHeight());

         int hour = (int)(elapsedSeconds/3600.0);
         int numberOfSecondsLeft = elapsedSeconds - 3600*hour;
         int minute = (int)(numberOfSecondsLeft/60.0);
         int second = numberOfSecondsLeft-60*minute;

         String hourString = String.valueOf(hour);
         String minuteString = (minute<10) ? "0" + String.valueOf(minute) : String.valueOf(minute);
         String secondString = (second<10) ? "0" + String.valueOf(second) : String.valueOf(second);

         int xwidth = 0;
      
         for (int i=0;i<hourString.length();i++) {
            String temp = hourString.substring(i,i+1);
            int digit = new Integer(temp).intValue();
            g.drawImage(digits[digit],10+xwidth,10,this);
            xwidth += digits[digit].getWidth(this);
         }

         g.drawImage(colon,10+xwidth,10,this);
         xwidth += colon.getWidth(this);

         for (int i=0;i<minuteString.length();i++) {
            String temp = minuteString.substring(i,i+1);
            int digit = new Integer(temp).intValue();
            g.drawImage(digits[digit],10+xwidth,10,this);
            xwidth += digits[digit].getWidth(this);
         }

         g.drawImage(colon,10+xwidth,10,this);
         xwidth += colon.getWidth(this);

         for (int i=0;i<secondString.length();i++) {
            String temp = secondString.substring(i,i+1);
            int digit = new Integer(temp).intValue();
            g.drawImage(digits[digit],10+xwidth,10,this);
            xwidth += digits[digit].getWidth(this);
         }
      }

      public void stop() {
         keepGoing = false;
         thread = null;
      }

      public void run() {
         while (keepGoing) {
            Calendar calendar = Calendar.getInstance();

            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);
            int second = calendar.get(Calendar.SECOND);

            elapsedSeconds = 3600*hour + 60*minute + second - baseTime;

            try {
               Thread.sleep(1000);
            } catch (InterruptedException ie) {
            }

            repaint();
         }
      }
   }

   public class Message extends JPanel {
      private String message;

      public Message(String message,
                     int width,
                     int height,
                     int x,
                     int y) {
         this.message = message;

         setSize(width,height);
         setLocation(x,y);
      }

      public void setText(String message) {
         this.message = message;
      }

      public void paint(Graphics g) {
         g.clearRect(0,0,getWidth(),getHeight());
         if (message != null)
            g.drawString(message,10,10);
      }

      public void redraw() {
         repaint();
      }
   }

   public class SolvePanel extends JPanel implements Runnable {
      private Thread thread;
      private boolean keepGoing;
      private int numberLeft;

      public SolvePanel(int width,
                        int height,
                        int x,
                        int y) {
         setSize(width,height);
         setLocation(x,y);

         setLayout(null);

         add(letters = new Choice());
         letters.setSize(50,20);
         letters.setLocation(90,10);

         letters.add("");

         for (int i=65;i<=90;i++)
            letters.add(String.valueOf((char)i));

         numberLeft = numberOfLettersLeft;

         setBackground(Color.white);
      }

      public void start() {
         if (thread != null)
            thread = null;
         thread = new Thread(this);
         keepGoing = true;
         thread.start();
      }

      public void paint(Graphics g) {
         g.drawString("Next Letter:",10,20);
      }

      public void redraw() {
         repaint();
      }

      public void run() {
         while (keepGoing) {
            boolean stop = false;

            int x = 0;
            int y = 0;

            Border originalBorder = buttons[0][0].getBorder();

            for (int i=0;i<words.length && !stop;i++)
               for (int j=0;j<words[i].length() && !stop;j++)
                  if (buttons[i][j].getText().equals("")) {
                     originalBorder = buttons[i][j].getBorder();
                     buttons[i][j].setBorder(BorderFactory.createLineBorder(Color.red));
                     stop = true;
                     x = i;
                     y = j;
                  }

            while (letters.getSelectedItem().equals(""));

            if (letters.getSelectedItem().toUpperCase().charAt(0) == characters[x][y]) {
               buttons[x][y].setText(String.valueOf(letters.getSelectedItem().toUpperCase().charAt(0)));
               buttons[x][y].setBorder(originalBorder);
               numberLeft--;
               letters.select(0);
               if (numberLeft == 0)
                  keepGoing = false;
            } else {
               buttons[x][y].setBorder(originalBorder);
               displayConsonants();
               currentPlayer.setScore(0);
               currentPlayer.unSetCurrent();
               currentPlayer = players[(currentPlayer.getNumber() + 1) % 3];
               currentPlayer.setCurrent();
               spin.setVisible(true);
               buyvowel.setVisible(true);
               solve.setVisible(true);
               keepGoing = false;
               restoreUnrevealed();
               solvePanel.setVisible(false);
               solvePanel = null;
            }
         }

         if (solvePanel != null) {
            solvePanel.setVisible(false);
            solvePanel = null;
         }

         if (numberLeft == 0) {
            message.setText(currentPlayer.getName() + " wins");
            message.redraw();
            timer.stop();
            applause.play();
         }
      }
   }

   public void init() {
      container = getContentPane();

      container.setLayout(null);

      container.setBackground(Color.white);

      Image[] images = getImages();

      players = new Player[3];

      alphabet = new JButton[26];

      sentinel = false;
      buyVowelSelected = false;
      solveSelected = false;

      gameOver = false;

      createAlphabet();

      displayConsonants();

      displayPhrase();

      applause = getAudioClip(getCodeBase(),"cheer.wav");

      boo = getAudioClip(getCodeBase(),"boo.wav");

      container.add(timer = new Timer(130,50,360,0));

      container.add(octagon = new Octagon(100,100,0,300));

      for (int i=0;i<3;i++) {
         container.add(players[i] = new Player(130,100,100+130*i,300));
         players[i].setImage(images[i]);
         players[i].redraw();
         players[i].setNumber(i);
      }

      players[0].setCurrent();

      currentPlayer = players[0];

      container.add(message = new Message("Please enter your names",250,20,0,230));

      bankrupt = false;

      container.add(start = createJButton("Start",50,20,100,270));
      container.add(spin = createJButton("Spin",50,20,160,270));
      container.add(buyvowel = createJButton("Buy Vowel",70,20,220,270));
      container.add(solve = createJButton("Solve",50,20,300,270));

      start.addActionListener(this);
      spin.addActionListener(this);
      buyvowel.addActionListener(this);
      solve.addActionListener(this);

      spin.setVisible(false);
      buyvowel.setVisible(false);
      solve.setVisible(false);

      timer.repaint();
      octagon.repaint();
      players[0].repaint();
      players[1].repaint();
      players[2].repaint();
   }

   public void start() {
      timer.start();
   }

   public void stop() {
      timer.stop();
   }

   public void destroy() {
      timer = null;
      
      for (int i=0;i<3;i++)
         players[i] = null;

      octagon = null;
   }
   
   public void actionPerformed(ActionEvent e) {
      if (!gameOver) {
         if (e.getSource() == start) {
            if (players[0].getName().equals("") ||
                players[1].getName().equals("") ||
                players[2].getName().equals("")) {
               message.setText("You must enter a name for each player.");
               message.redraw();
            } else {
               start.setVisible(false);
               spin.setVisible(true);
               buyvowel.setVisible(true);
               solve.setVisible(true);
               message.setText("");
               message.redraw();
            }
         }
         if (e.getSource() == spin) {
            spin.setVisible(false);
            buyvowel.setVisible(false);
            solve.setVisible(false);
            int number = (int)(10*Math.random()) + 10;
            System.out.println(number);
            octagon.start(number);
         }
         if (e.getSource() == solve) {
            container.add(solvePanel = new SolvePanel(200,50,250,210));
            solvePanel.redraw();
            solvePanel.start();
            removeAllLetters();
            spin.setVisible(false);
            buyvowel.setVisible(false);
            solve.setVisible(false);
         }
         if (sentinel) {
            for (int i=0;i<alphabet.length;i++)
               if (e.getSource() == alphabet[i]) {
                  spin.setVisible(true);
                  buyvowel.setVisible(true);
                  solve.setVisible(true);
                  used[i] = true;
                  alphabet[i].setVisible(false);
                  int numberFound = displayLetter(alphabet[i].getText());
                  numberOfLettersLeft -= numberFound;
                  if (!vowel((char)(i+65)))
                     currentPlayer.increaseScore(currentAmount*numberFound);
                  else {
                     if (numberFound == 0)
                        currentPlayer.reduceScore(500);
                     else
                        currentPlayer.reduceScore(250);
                  }
                  if (numberFound == 0) {
                     currentPlayer.unSetCurrent();
                     currentPlayer = players[(currentPlayer.getNumber() + 1) % 3];
                     currentPlayer.setCurrent();
                     boo.play();
                  } else
                     applause.play();
               }
            if (numberOfLettersLeft == 0) {
               gameOver = true;
               message.setText(currentPlayer.getName() + " wins");
               message.redraw();
               timer.stop();
               applause.play();
            } else {
               if (numberOfConsonantsLeft() == 0) {
                  spin.setVisible(false);
                  displayVowels();
               } else
                  displayConsonants();
               sentinel = false;
            }         
         } else if (e.getSource() == buyvowel) {
            spin.setVisible(false);
            buyvowel.setVisible(false);
            solve.setVisible(false);
            displayVowels();
            sentinel = true;
         }
      }
   }
}