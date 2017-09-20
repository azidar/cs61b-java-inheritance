package inheritance.fields;

/* Java Field Inheritance
   Rule:
     1) look at instance's STATIC type to find an instance or static FIELD.
     2) if it does not contain the field, look at instance's static type's parent, etc.
   Inheritance form: HIDING
 */

public class A {
    public int i = 0;
    public int j = -1;
}

class B extends A {
    public int i = 1;
}

class Main {
    public static void main(String[] args) {
        /* Declare instance x, which has static type B and dynamic type B */
        B x = new B();

        /* Example 1: Missing field in subclass */
        // Note that field j is missing in subclass B, but implemented in A
        System.out.println("Example 1: " + x.j); // Can't find j in B, so look in B's parent, A. Found it!

        /* Example 2: Field implemented in both parent and its subclass */
        System.out.println("Example 2: " + x.i); // Look in static class B, and found i B! Look no further!

        /* Example 3: Field implemented in both parent and subclass. Static type is parent, dynamic type is child. */
        System.out.println("Example 3: " + ((A) x).i); // Ignore dynamic type, start from static type, A. Found it!

        /* This form of inheritance is called HIDING, not OVERRIDING.
           Like how static methods are inherited, fields are inherited in the same way.
           B.i does not eliminate A.i; instead, it "hides" it based off of the static type.
           Thus, unlike instance method inheritance where it is impossible to call a parent's overridden instance method from an instance of the child,
              hiding picks based off of the static type and thus, by casting it, we can find either A.i or B.i.
        */

    }
}
