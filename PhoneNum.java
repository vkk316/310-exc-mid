
// PhoneNum.java
// Andrew Davison, ad@fivedots.coe.psu.ac.th, Jan. 2022


/* A U.S. phone number with an area (3 digits), exchange (3 digits),
   and extension (4 digits).

   This class implements its own hashCode() and equals().
 */


import java.util.*;



public class PhoneNum
{


  private int area;       // area code (3 digits)
  private int exchange;   // exchange code (3 digits)
  private int ext;        // extension code (4 digits)
  private int hashConstant;


  public PhoneNum(int area, int exchange, int ext, int hashConstant)
  {
    this.area = area;
    this.exchange = exchange;
    this.ext = ext;
    this.hashConstant = hashConstant;
  }


  public String toString()
  {
    // 0 for padding with digits with leading 0s
    return String.format("(%03d) %03d-%04d", area, exchange, ext);
  }


  public int hashCode()
  {  return hashConstant*(area + hashConstant*exchange) + ext;  }

  public void setName(int hashCon)
    {
  
        // This keyword refers to current instance itself
        this.hashConstant = hashCon;
    }




  public boolean equals(Object other)
  // Compare this phone number to another one
  {
    if (other == this) 
      return true;
    if (other == null) 
      return false;
    if (other.getClass() != this.getClass()) 
      return false;
    PhoneNum pn = (PhoneNum) other;
    return (this.area == pn.area) && 
           (this.exchange == pn.exchange) &&
           (this.ext == pn.ext);
  }



  // ---------------------------- test rig -----------------


  public static void main(String[] args)
  {
    PhoneNum a = new PhoneNum(609, 258, 4455, 31);
    PhoneNum b = new PhoneNum(609, 876, 5309, 31);
    PhoneNum c = new PhoneNum(609, 555, 5309, 31);
    PhoneNum d = new PhoneNum(215, 876, 5309, 31);
    PhoneNum e = new PhoneNum(609, 876, 5309, 31);   // same as b

    System.out.println("a = " + a);
    System.out.println("b = " + b);
    System.out.println("c = " + c);
    System.out.println("d = " + d);
    System.out.println("e = " + e);

    System.out.println("a.hashCode(): " + a.hashCode());
    System.out.println("b.hashCode(): " + b.hashCode());
    System.out.println("e.hashCode(): " + e.hashCode());
    
    System.out.println("b == e: " + (b == e));
    System.out.println("b.equals(e): " + (b.equals(e)));
  }  // end of main()

}  // end of PhoneNum class
