package leetcode.stringmanipulation;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/unique-email-addresses/
 * 
 * @author z003xfbr
 *
 *         Every email consists of a local name and a domain name, separated by the @ sign.
 * 
 *         For example, in alice@leetcode.com, alice is the local name, and leetcode.com is the domain name.
 * 
 *         Besides lowercase letters, these emails may contain '.'s or '+'s.
 * 
 *         If you add periods ('.') between some characters in the local name part of an email address, mail sent there
 *         will be forwarded to the same address without dots in the local name. For example, "alice.z@leetcode.com" and
 *         "alicez@leetcode.com" forward to the same email address. (Note that this rule does not apply for domain
 *         names.)
 * 
 *         If you add a plus ('+') in the local name, everything after the first plus sign will be ignored. This allows
 *         certain emails to be filtered, for example m.y+name@email.com will be forwarded to my@email.com. (Again, this
 *         rule does not apply for domain names.)
 * 
 *         It is possible to use both of these rules at the same time.
 * 
 *         Given a list of emails, we send one email to each address in the list. How many different addresses actually
 *         receive mails?
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: ["test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"]
 *         Output: 2
 *         Explanation: "testemail@leetcode.com" and "testemail@lee.tcode.com" actually receive mails
 * 
 * 
 *         Note:
 * 
 *         1 <= emails[i].length <= 100
 *         1 <= emails.length <= 100
 *         Each emails[i] contains exactly one '@' character.
 *         All local and domain names are non-empty.
 *         Local names do not start with a '+' character.
 *
 */
public class UniqueEmailAddresses {

  public static int numUniqueEmails(final String[] emails) {

    final Set<String> mySet = new HashSet<>();

    if (emails != null && emails.length > 0) {
      for (final String email : emails) {

        final String[] temp = email.split("@");
        String mailProcess = "";

        if (temp.length > 0) {

          String name = temp[0];
          final int posPlus = name.indexOf("+");
          if (posPlus > 0) {
            name = name.replace(name.substring(posPlus, name.length()), "");
          }
          name = name.replace(".", "");

          mailProcess = name;
          if (temp.length > 1) {
            mailProcess += "@" + temp[1];
          }
        }
        System.out.println("TEMP " + mailProcess);
        mySet.add(mailProcess);

      }
    }

    return mySet.size();
  }

  public static void main(final String[] args) {
    // String[] emails = {"alice.z@leetcode.com", "alicez@leetcode.com",
    // "m.y+name@email.com", "my@email.com"}; // 2
    final String[] emails = { "test.email+alex@leetcode.com", "test.e.mail+bob.cathy@leetcode.com",
        "testemail+david@lee.tcode.com" };// 2
    System.out.println(numUniqueEmails(emails));

  }

}
