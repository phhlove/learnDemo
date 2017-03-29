package com.phh.learnDemo.xstream;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 
 *
 * @Description:xml bean json
 * @author phh
 * @date 2016年8月23日
 *
 */
public class XstreamTest {

	@XStreamAlias("person")
	public class Person {
		@XmlElement
	    @XStreamAlias("名称")
		private String name;
		private int phoneNuber;
		private List<Address> addresses = new ArrayList<>();

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getPhoneNuber() {
			return phoneNuber;
		}

		public void setPhoneNuber(int phoneNuber) {
			this.phoneNuber = phoneNuber;
		}

		public List<Address> getAddresses() {
			return addresses;
		}

		public void setAddresses(List<Address> addresses) {
			this.addresses = addresses;
		}

	}

	@XStreamAlias("address")
	public class Address {
		private String street;
		private int houseNo;

		public String getStreet() {
			return street;
		}

		public void setStreet(String street) {
			this.street = street;
		}

		public int getHouseNo() {
			return houseNo;
		}

		public void setHouseNo(int houseNo) {
			this.houseNo = houseNo;
		}

	}

	public static void toEntity(String inputXML) {
		XStream xs = new XStream();
		// 这句和@XStreamAlias("person")等效
		// xs.alias("person",Person.class);
		// xs.alias("address",Address.class);
		xs.setMode(XStream.NO_REFERENCES);
		// 这句和@XStreamImplicit()等效
		// xs.addImplicitCollection(Person.class,"addresses");
		// 这句和@XStreamAsAttribute()
		// xs.useAttributeFor(Person.class, "name");
		// 注册使用了注解的VO
//		xs.processAnnotations(new Class[] { Person.class, Address.class });
		Person person = (Person) xs.fromXML(inputXML);
		System.out.println(person.getAddresses().get(0).getHouseNo() + person.getName());

	}

	public static String toXML(){  
        XStream xStream = new XStream();  
        Person person = new XstreamTest().new Person();  
        person.setName("rojer");  
        person.setPhoneNuber(999);  
        Address address1 = new XstreamTest().new Address();  
        address1.setHouseNo(888);  
        address1.setStreet("newyork");  
        Address address2 = new XstreamTest().new Address();  
        address2.setHouseNo(76767);  
        address2.setStreet("toyo");  
        person.getAddresses().add(address1);  
        person.getAddresses().add(address2);  
//        xStream.alias("person", Person.class);  
//        xStream.alias("address",Address.class);  
        xStream.setMode(XStream.NO_REFERENCES);  
//        xStream.addImplicitCollection(Person.class, "addresses");  
//        xStream.useAttributeFor(Person.class,"name");  
        //注册使用了注解的VO  
//        xStream.processAnnotations(new Class[]{Person.class,Address.class});  
        String xml = xStream.toXML(person);  
  
        System.out.println(xml);  
        return xml;  
    }  
	
	public static void main(String[] args) throws Exception {
//		String res="<Result>"+"<totalCount>0</totalCount>"+"</Result>"; 
//		StringReader reader = new StringReader(res);
//		XStream xstream = new XStream();
//		ObjectInputStream in = xstream.createObjectInputStream(reader);
//		Result stu = (Result) in.readObject();
//		byte i = in.readByte();
//		boolean bo = in.readBoolean();
//		float f = in.readFloat();
//		String str = in.readUTF();
//		System.out.println(stu);
//		System.out.println(i);
//		System.out.println(bo);
//		System.out.println(f);
//		System.out.println(str);
		testBean2XML();
	}
	
	
	public class Result implements Serializable { 
	private static final long serialVersionUID = 5649683447795092580L; 
	private int totalCount; 
	public int getTotalCount() { 
	return totalCount; 
	} 
	public void setTotalCount(int totalCount) { 
	this.totalCount = totalCount; 
	}
	@Override
	public String toString() {
		return "Result [totalCount=" + totalCount + "]";
	} 
	
	} 

	 public static void testBean2XML() {
	        System.out.println("将Java对象转换为xml！\n");
	        Person person = new XstreamTest().new Person(); 
	        person.setName("qwe");
	        person.setPhoneNuber(123);
	        XStream xstream = new XStream(); 
	        xstream.alias("address", XstreamTest.Person.class);
	        String xml = xstream.toXML(person); 
	        System.out.println(xml); 

//	        System.out.println("\n将xml转换为Java对象！");
//	        Persons cre_person = (Persons) xstream.fromXML(xml);
//	        System.out.println(cre_person.toString()); 
	    } 
	
}
