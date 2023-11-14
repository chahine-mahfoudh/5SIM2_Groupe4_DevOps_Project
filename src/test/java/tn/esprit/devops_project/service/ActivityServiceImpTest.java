package tn.esprit.devops_project.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.devops_project.entities.ActivitySector;
import tn.esprit.devops_project.repositories.ActivitySectorRepository;
import tn.esprit.devops_project.services.ActivitySectorImpl;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class ActivityServiceImpTest {
    @Mock
    private ActivitySectorRepository activityRepository;

    @InjectMocks
    private ActivitySectorImpl activityService;


    @Test
    void testAddActivity() {
        ActivitySector activity = ActivitySector.builder()
                .codeSecteurActivite("hamza")
                .libelleSecteurActivite("abidi")
                .build();

        mockSaveWithGeneratedId();

        ActivitySector savedActivity = activityService.addActivitySector(activity);

        verify(activityRepository, times(1)).save(Mockito.any(ActivitySector.class));

        assertActivityFields(savedActivity, "hamza", "abidi");
    }

    @Test
    void testRetrieveActivity() {
        ActivitySector activity = ActivitySector.builder()
                .idSecteurActivite(1L) // Assuming an ID for testing
                .codeSecteurActivite("rania")
                .libelleSecteurActivite("Smith")
                .build();

        // Mock the behavior of the repository when retrieving the activity
        when(activityRepository.findById(activity.getIdSecteurActivite()))
                .thenReturn(java.util.Optional.of(activity));

        // Retrieve the activity
        ActivitySector retrievedActivitySector = activityService.retrieveActivitySector(activity.getIdSecteurActivite());

        // Verify that the repository's findById method was called with the correct argument
        verify(activityRepository, times(1)).findById(activity.getIdSecteurActivite());

        // Check assertions
        assertActivityFields(retrievedActivitySector, "rania", "Smith");
    }

    private void mockSaveWithGeneratedId() {
        when(activityRepository.save(Mockito.any(ActivitySector.class))).thenAnswer(invocation -> {
            ActivitySector savedActivity = invocation.getArgument(0);
            savedActivity.setIdSecteurActivite(generateNonNullOrUniqueID());
            return savedActivity;
        });
    }

    private void assertActivityFields(ActivitySector activity, String expectedCode, String expectedLibelle) {
        assertNotNull(activity.getIdSecteurActivite());
        assertEquals(expectedCode, activity.getCodeSecteurActivite());
        assertEquals(expectedLibelle, activity.getLibelleSecteurActivite());
    }

    private Long generateNonNullOrUniqueID() {
        // Replace this with your logic to generate a non-null and unique ID.
        // For simplicity, using the current system time in milliseconds.
        return System.currentTimeMillis();
    }
}
