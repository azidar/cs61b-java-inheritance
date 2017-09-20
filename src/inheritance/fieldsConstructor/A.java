package inheritance.fieldsConstructor;

// To get the OVERRIDING behavior for fields default values, we cannot use HIDING. Instead, the convention is to
//    use the constructor to set default values for fields, instead of re-declaring the field and assigning it a
//    value.

class A {
    int i;
    int j;
    A() {
        // Note that this.i is a lookup based on the static type of this, which is A.
        this.i = 0;
        this.j = -1;
    }
}

class B extends A {
    B() {
        // First, we call A's constructor, which sets default values for i and j
        super(); // By default this is called already, see Lecture 7, slide 13
        // Then, we set a new default value for i.
        // Note that this lookup depends on the static type of this, which is B!
        //   So, we look up whether B has an i, which it does not, then we look up whether A has i, which it does.
        this.i = 1;
    }
}

class Main {
    public static void main(String[] args) {
        // Example 1: field j is missing in subclass B, but implemented in A
        B x = new B(); // x has a static type B and dynamic type B
        System.out.println("Example 1: " + x.j); // Can't find j in B, so look in B's parent, A. Found it!

        // Example 2: field i is only in A
        System.out.println("Example 2: " + x.i); // Can't find i in B, so look in B's parent, A. Found it!

        // Example 3: field i is only in A. Static type is A, dynamic type is B.
        System.out.println("Example 3: " + ((A) x).i); // Ignore dynamic type, start from static type, A. Found it!
        // Note that we return the correct value 1, because we set it in B's constructor.
    }
}
