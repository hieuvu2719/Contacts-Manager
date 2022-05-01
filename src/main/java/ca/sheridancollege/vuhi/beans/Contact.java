package ca.sheridancollege.vuhi.beans;

import lombok.*;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Contact {

    public Contact(long id) {
        this.setId(id);
    }

    public Long id;
    @NonNull
    private String name;
    @NonNull
    private Long phoneNumber;
    @NonNull
    private String address;
    @NonNull
    private String email;
    @NonNull
    private String role;
}

