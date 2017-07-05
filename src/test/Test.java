package test;

import model.ReadingMaterial;
import model.UserType;
import service.ReadingMaterialService;

public class Test {
	
	public static void main(String[] args) {
		
		ReadingMaterial rm  = ReadingMaterialService.getRMByID("ABC-123", UserType.STUDENT);
		
		System.out.println(rm.getTitle());
		System.out.println(rm.getAuthor());
		System.out.println(rm.getPublisher());
		System.out.println(rm.getYear());
		System.out.println(rm.getRMType()+"");
		
		System.out.println("\n" + rm.getStatus() + "\n");
		
		System.out.println("TAGS: ");
		
		for(int i = 0; i < rm.getTags().size(); i ++) {
			System.out.println(rm.getTags().get(i).getTag());
		}
		
		System.out.println("\nREVIEWS:");
		
		for(int i = 0; i < rm.getReviews().size(); i ++) {
			System.out.println(rm.getReviews().get(i).getReview());
		}
	}

}
