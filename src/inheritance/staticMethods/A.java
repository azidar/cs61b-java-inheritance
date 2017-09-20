package inheritance.staticMethods;

/* Java Static Method Inheritance
   Rule:
     1) look at instance's STATIC type to find STATIC method
     2) if it does not contain static method, look at instance's static type's parent, etc.
   Inheritance form: HIDING
 */

public class A {
    public static String f() {
        return "Called A's static f";
    }
    public static String g() {
        return "Called A's static g";
    }
}

class B extends A {
    public static String f() {
        return "Called B's static f";
    }
}

class Main {
    public static void main(String[] args) {
        /* Declare instance x, which has static type B and dynamic type B */
        B x = new B();

        /* Example 1: Missing static method in subclass */
        System.out.println("Example 1: " + x.g()); // Can't find g in B, so look in B's parent, A. Found it!

        /* Example 2: Static method is implemented in both A and its subclass, B */
        System.out.println("Example 2: " + x.f()); // Found f in g! Look no further!

        /* Example 3: Static method is implemented in both A and B. Static type is A, dynamic type is B. */
        System.out.println("Example 3: " + ((A) x).f()); // Ignore dynamic type, start from static type, A. Found it!

        /* This form of inheritance is called HIDING, not OVERRIDING.
           Because we use the static type to lookup a static method, it causes B.f to not eliminate A.f; instead, it
               "hides" it based off of the static type.
           Unlike instance method inheritance where it is impossible to call a parent's overridden instance method from
              an instance of the child, hiding picks based off of the static type and thus, by casting it, we can find it.
        */

        /* If you think this is confusing, join the club! Having different forms of inheritance (overriding vs hiding)
              for instance/static methods was an implementation decision made by the Java developers. They were worried
              about the performance overheads of overriding behavior, and decided to instead implement hiding for static
              methods, which could theoretically be optimized better.
           Not only is this confusing, it turns out that IT ISN'T EVEN FASTER!!! Modern compilers have figured out how
              to optimize overriding implementations so that they are no slower than hiding. Thus, we unfortunately have
              this bad language design, and the benefits of which are no longer relevant.
           Other languages only implement overriding behavior, so from a learning perspective, hiding is antiquated and
              no longer too relevant for theory reasons. However, because so much legacy Java code relies on hiding
              behavior, it is impossible to change for Java, so for practical reasons it is still important to
              understand.
        */
    }
}
