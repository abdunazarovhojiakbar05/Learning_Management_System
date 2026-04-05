  package entity;

  import lombok.*;


   @Getter
   @Setter
   @ToString(of = {"courseName", "price", "duration", "startTime", "endTime", "mentor"})
   @NoArgsConstructor
   @AllArgsConstructor


  public class Course {

    private String courseId;
    private String courseName;
    private int price;
    private String duration;
    private String startTime;
    private String endTime;
    private User mentor;


   }
