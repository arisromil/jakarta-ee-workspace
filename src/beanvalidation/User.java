public class User {

    @NotBlank
    private String name;

    @Email
    private String email;

    @NotEmpty
    private List<@PositiveOrZero Integer> profileId;

    public User(String name, String email, List<Integer> profileId) {
        this.name = name;
        this.email = email;
        this.profileId = profileId;
    }

}