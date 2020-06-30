public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> myDeque = new ArrayDeque<>();
        for (int i = 0; i < word.length(); i++ ) {
            myDeque.addLast(word.charAt(i));
        }
        return myDeque;
    }
    public boolean isPalindrome(String word) {
        return isPalindromeHelper(wordToDeque(word));
    }
    private boolean isPalindromeHelper(Deque<Character> myDeque) {
        if (myDeque.size() == 0 || myDeque.size() == 1) {
            return true;
        }
        Character firstChar = myDeque.removeFirst();
        Character lastChar = myDeque.removeLast();
        return firstChar == lastChar && isPalindromeHelper(myDeque);
    }
    public boolean isPalindrome(String word, CharacterComparator cc) {
        return isPalindromHelperOffbyN(wordToDeque(word), cc);
    }

    private boolean isPalindromHelperOffbyN(Deque<Character> myDeque, CharacterComparator cc) {
        if (myDeque.size() == 0 || myDeque.size() == 1) {
            return true;
        }
        Character firstChar = myDeque.removeFirst();
        Character lastChar = myDeque.removeLast();
        return cc.equalChars(firstChar, lastChar) && isPalindromHelperOffbyN(myDeque, cc);
    }
}
