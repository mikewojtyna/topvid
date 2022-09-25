package pl.wojtyna.topvid.account;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("User account changes can be undone")
class UndoUserAccountTest {

    // @formatter:off
    @DisplayName(
        """
         given user account named originally George Martin
         and then George decided to change his name to Henry,
         when undo changes,
         then account is again named George Martin
        """
    )
    // @formatter:on
    @Test
    void undoFirstNameChangeTest() {
        // given
        var userAccountRepository = new InMemoryUserAccountRepository();
        var snapshotRepository = new InMemoryUserAccountSnapshotRepository();
        var userAccountEditor = new UserAccountEditor(userAccountRepository, snapshotRepository);
        var userAccount = userAccountEditor.createAccount("George",
                                                          "Martin",
                                                          LocalDate.of(1950, 12, 13));
        userAccountEditor.updateFirstName(userAccount.getId(), "Henry");
        assertThat(userAccountRepository.byId(userAccount.getId())).hasValueSatisfying(userAccountAfterUndo -> assertThat(
            userAccountAfterUndo.toString()).isEqualTo("Henry Martin"));

        // when
        userAccountEditor.undo(userAccount.getId());

        // then
        assertThat(userAccountRepository.byId(userAccount.getId())).hasValueSatisfying(userAccountAfterUndo -> assertThat(
            userAccountAfterUndo.toString()).isEqualTo("George Martin"));
    }

    // @formatter:off
    @DisplayName(
        """
         given user account named originally George Martin
         and then George decided to change his name to Henry
         and then to Thomas
         and then to Rodrick,
         when undo changes 3 times,
         then account is named respectively Thomas Martin; Henry Martin and finally George Martin
        """
    )
    // @formatter:on
    @Test
    void undoFirstNameThreeTimesChangeTest() {
        // given
        var userAccountRepository = new InMemoryUserAccountRepository();
        var snapshotRepository = new InMemoryUserAccountSnapshotRepository();
        var userAccountEditor = new UserAccountEditor(userAccountRepository, snapshotRepository);
        var userAccount = userAccountEditor.createAccount("George",
                                                          "Martin",
                                                          LocalDate.of(1950, 12, 13));
        userAccountEditor.updateFirstName(userAccount.getId(), "Henry");
        userAccountEditor.updateFirstName(userAccount.getId(), "Thomas");
        userAccountEditor.updateFirstName(userAccount.getId(), "Rodrick");
        assertThat(userAccountRepository.byId(userAccount.getId())).hasValueSatisfying(userAccountAfterUndo -> assertThat(
            userAccountAfterUndo.toString()).isEqualTo("Rodrick Martin"));

        // when
        userAccountEditor.undo(userAccount.getId());

        // then
        assertThat(userAccountRepository.byId(userAccount.getId())).hasValueSatisfying(userAccountAfterUndo -> assertThat(
            userAccountAfterUndo.toString()).isEqualTo("Thomas Martin"));

        // when
        userAccountEditor.undo(userAccount.getId());

        // then
        assertThat(userAccountRepository.byId(userAccount.getId())).hasValueSatisfying(userAccountAfterUndo -> assertThat(
            userAccountAfterUndo.toString()).isEqualTo("Henry Martin"));

        // when
        userAccountEditor.undo(userAccount.getId());

        // then
        assertThat(userAccountRepository.byId(userAccount.getId())).hasValueSatisfying(userAccountAfterUndo -> assertThat(
            userAccountAfterUndo.toString()).isEqualTo("George Martin"));
    }
}
