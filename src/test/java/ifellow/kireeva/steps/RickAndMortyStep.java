package ifellow.kireeva.steps;

import ifellow.kireeva.client.RickAndMortyClient;
import ifellow.kireeva.dto.rickandmorty.character.CharacterRIM;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Тогда;

import static org.junit.jupiter.api.Assertions.*;


public class RickAndMortyStep {

    private final RickAndMortyClient client = new RickAndMortyClient();
    private CharacterRIM targetCharacter;
    private CharacterRIM lastCharacterInEpisode;
    private String characterName;

    @Дано("персонаж по имени {string}")
    public void searchCharacterByName(String name) {
        this.characterName = name;
        this.targetCharacter = client.getCharacterByName(name);
    }

    @Тогда("персонаж должен быть найден")
    public void verifyCharacterIsFound() {
        assertNotNull(targetCharacter, "Персонаж не найден в API");
        assertEquals(characterName, targetCharacter.getName(), "Найден не тот персонаж");
    }

    @И("у персонажа должно быть хотя бы одно появление в эпизоде")
    public void verifyCharacterHasEpisodes() {
        assertFalse(targetCharacter.getEpisode().isEmpty(), "У персонажа нет эпизодов");
    }

    @И("найти последнего персонажа в этом эпизоде")
    public void findLastCharacterInEpisode() {
        String lastEpisodeUrl = targetCharacter.getEpisode().get(targetCharacter.getEpisode().size() - 1);
        String episodeId = RickAndMortyClient.extractIdFromUrl(lastEpisodeUrl);

        this.lastCharacterInEpisode = client.getLastCharacterFromEpisode(episodeId);
        assertNotNull(lastCharacterInEpisode, "Последний персонаж в эпизоде не найден");

    }

    @Тогда("раса выбранного персонажа и последнего персонажа должна совпадать")
    public void verifySpeciesMatch() {
        String targetSpecies = targetCharacter.getSpecies();
        String lastCharSpecies = lastCharacterInEpisode.getSpecies();

        assertEquals(targetSpecies, lastCharSpecies, "Раса персонажей должна совпадать");

    }

    @И("их локации должны отличаться")
    public void verifyLocationsDiffer() {
        String targetLocation = targetCharacter.getLocation().getName();
        String lastCharLocation = lastCharacterInEpisode.getLocation().getName();

        assertNotEquals(targetLocation, lastCharLocation, "Локации персонажей должны отличаться");

    }
}