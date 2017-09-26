package inheritance.instanceMethods;

/* Java Instance Method Inheritance
   Rule:
     1) look at instance's DYNAMIC type to find INSTANCE method
     2) if it does not contain instance method, look at instance's dynamic type's parent, etc.
   Inheritance form: OVERRIDING
 */

public class A {
    public String f() {
        return "Called A's f";
    }

    public static String g() {
        return "Called A's static g";
    }
}

class B extends A {
    public String f() {
        return "Called B's f";
    }

}

class C extends A {
    public String f() {
        return "From C's f: " + super.f();
    }
}

class Main {
    public static void main(String[] args) {
        /* Declare instance x, which has static type B and dynamic type B */
        B x = new B();

        /* Example 1: Missing instance method */
        System.out.println("Example 1: " + x.g()); // Can't find g in B, so look in B's parent, A. Found it!

        /* Example 2: Overridden instance method */
        // Note that the f() method is implemented in both A and its subclass, B. Thus, B's f() OVERRIDES A's f()
        System.out.println("Example 2: " + x.f()); // Found f in B! Look no further!

        /* Example 3: Overridden instance method with different static type */
        System.out.println("Example 3: " + ((A) x).f()); // Ignore static type, start from dynamic type, A. Found it!

        /* Note here that it is impossible to call A's implementation of f() from a instance with dynamic type B.
               Casting it doesn't work! Truly, B's f() completely OVERRIDES A's f().
           If we wanted to call A's implementation of f() WITHIN B's CLASS DECLARATION, we can use the 'super' syntax.
         */

        /* Example 4: Using super to call parent implementation */
        C y = new C();
        System.out.println("Example 4: " + y.f());
    }
}
