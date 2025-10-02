package ifellow.kireeva;

import ifellow.kireeva.dto.rickandmorty.character.CharacterRIM;
import ifellow.kireeva.steps.RickAndMortySteps;
import ifellow.kireeva.utils.Config;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

public class RickAndMortyTests {

    private static final Logger log = Logger.getLogger(RickAndMortyTests.class.getName());
    private static RickAndMortySteps steps;
    private static String characterName;

    @BeforeAll
    static void setUp() {
        steps = new RickAndMortySteps();
        characterName = Config.getProperty("rick_and_morty.character_name");
    }

    @Test
    void rickAndMortyApiTest() {
        CharacterRIM characterRIM = steps.getCharacterByName(characterName);

        assertNotNull(characterRIM, "Персонаж не найден в API");
        assertEquals(characterName, characterRIM.getName(), "Найден не тот персонаж");
        log.info(characterName + " найден.");

        assertFalse(characterRIM.getEpisode().isEmpty(), "У персонажа нет эпизодов");
        String lastEpisodeUrl = characterRIM.getEpisode().get(characterRIM.getEpisode().size() - 1);
        String episodeId = RickAndMortySteps.extractIdFromUrl(lastEpisodeUrl);
        log.info("Последний эпизод персонажа: ID " + episodeId);

        CharacterRIM lastCharacter = steps.getLastCharacterFromEpisode(episodeId);
        assertNotNull(lastCharacter, "Последний персонаж в эпизоде не найден");
        log.info("Последний персонаж: " + lastCharacter.getName());

        String mortySpecies = characterRIM.getSpecies();
        String lastCharSpecies = lastCharacter.getSpecies();
        String mortyLocationName = characterRIM.getLocation().getName();
        String lastCharLocationName = lastCharacter.getLocation().getName();

        log.info("Раса Морти: " + mortySpecies);
        log.info("Раса последнего персонажа: " + lastCharSpecies);
        log.info("Локация Морти: " + mortyLocationName);
        log.info("Локация последнего персонажа: " + lastCharLocationName);


        assertEquals(mortySpecies, lastCharSpecies, "Раса Морти и последнего персонажа должна совпадать");
        log.info("Расы совпадают: " + mortySpecies);

        assertNotEquals(mortyLocationName, lastCharLocationName, "Местоположение Морти и последнего персонажа должно отличаться");
        log.info("Локации различаются: " + mortyLocationName + " ≠ " + lastCharLocationName);

    }
}