package pl.wojtyna.topvid.account;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import pl.wojtyna.topvid.common.domain.UserId;
import pl.wojtyna.topvid.patterns.MementoPattern;

import java.time.LocalDate;
import java.util.UUID;

@EqualsAndHashCode(of = "id")
public class UserAccount {

    @NonNull
    private final UserId id;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    private LocalDate birthDate;

    public UserAccount(@NonNull UserId id,
                       @NonNull String firstName,
                       @NonNull String lastName,
                       @NonNull LocalDate birthDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "%s %s".formatted(firstName, lastName);
    }

    public UserId getId() {
        return id;
    }

    public void setFirstName(@NonNull String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(@NonNull String lastName) {
        this.lastName = lastName;
    }

    public void setBirthDate(@NonNull LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @MementoPattern
    public Snapshot createSnapshot() {
        return new Snapshot(UUID.randomUUID().toString(), firstName, lastName, birthDate);
    }

    @MementoPattern
    public void restore(@NonNull Snapshot snapshot) {
        setFirstName(snapshot.firstName);
        setLastName(snapshot.lastName);
        setBirthDate(snapshot.birthDate);
    }

    @MementoPattern
    public static class Snapshot {

        @NonNull
        private final String firstName;
        @NonNull
        private final String lastName;
        @NonNull
        private final LocalDate birthDate;
        @NonNull
        private final String id;

        private Snapshot(@NonNull String id,
                         @NonNull String firstName,
                         @NonNull String lastName,
                         @NonNull LocalDate birthDate) {
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
            this.birthDate = birthDate;
        }

        public String getId() {
            return id;
        }
    }

}
