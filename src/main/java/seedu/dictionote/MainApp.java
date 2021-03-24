package seedu.dictionote;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.dictionote.commons.core.Config;
import seedu.dictionote.commons.core.LogsCenter;
import seedu.dictionote.commons.core.Version;
import seedu.dictionote.commons.exceptions.DataConversionException;
import seedu.dictionote.commons.util.ConfigUtil;
import seedu.dictionote.commons.util.StringUtil;
import seedu.dictionote.logic.Logic;
import seedu.dictionote.logic.LogicManager;
import seedu.dictionote.model.ContactsList;
import seedu.dictionote.model.DefinitionBook;
import seedu.dictionote.model.Dictionary;
import seedu.dictionote.model.Model;
import seedu.dictionote.model.ModelManager;
import seedu.dictionote.model.NoteBook;
import seedu.dictionote.model.ReadOnlyContactsList;
import seedu.dictionote.model.ReadOnlyDefinitionBook;
import seedu.dictionote.model.ReadOnlyDictionary;
import seedu.dictionote.model.ReadOnlyNoteBook;
import seedu.dictionote.model.ReadOnlyUserPrefs;
import seedu.dictionote.model.UserPrefs;
import seedu.dictionote.model.util.SampleDataUtil;
import seedu.dictionote.storage.ContactsListStorage;
import seedu.dictionote.storage.DefinitionBookStorage;
import seedu.dictionote.storage.DictionaryStorage;
import seedu.dictionote.storage.JsonContactsListStorage;
import seedu.dictionote.storage.JsonDefinitionBookStorage;
import seedu.dictionote.storage.JsonDictionaryStorage;
import seedu.dictionote.storage.JsonNoteBookStorage;
import seedu.dictionote.storage.JsonUserPrefsStorage;
import seedu.dictionote.storage.NoteBookStorage;
import seedu.dictionote.storage.Storage;
import seedu.dictionote.storage.StorageManager;
import seedu.dictionote.storage.UserPrefsStorage;
import seedu.dictionote.ui.Ui;
import seedu.dictionote.ui.UiManager;

/**
 * Runs the application.
 */
public class MainApp extends Application {

    public static final Version VERSION = new Version(1, 2, 1, true);

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    protected Ui ui;
    protected Logic logic;
    protected Storage storage;
    protected Model model;
    protected Config config;

    @Override
    public void init() throws Exception {
        logger.info("=============================[ Initializing AddressBook ]===========================");
        logger.info("=============================[ Initializing NoteBook ]==============================");
        logger.info("=============================[ Initializing Dictionary ]============================");
        logger.info("=============================[ Initializing DefinitionBook ]========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        ContactsListStorage contactsListStorage = new JsonContactsListStorage(userPrefs.getContactsListFilePath());
        NoteBookStorage noteBookStorage = new JsonNoteBookStorage(userPrefs.getNoteBookFilePath());
        DictionaryStorage dictionaryStorage = new JsonDictionaryStorage(userPrefs.getDictionaryFilePath());
        DefinitionBookStorage definitionBookStorage =
                new JsonDefinitionBookStorage(userPrefs.getDefinitionBookFilePath());
        storage = new StorageManager(contactsListStorage, userPrefsStorage, noteBookStorage,
                dictionaryStorage, definitionBookStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s dictionote book and {@code userPrefs}. <br>
     * The data from the sample dictionote book will be used instead if {@code storage}'s dictionote book is not found,
     * or an empty dictionote book will be used instead if errors occur when reading {@code storage}'s dictionote book.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyContactsList> addressBookOptional;
        ReadOnlyContactsList initialDataAddress;
        Optional<ReadOnlyNoteBook> noteBookOptional;
        ReadOnlyNoteBook initialDataNote;
        Optional<ReadOnlyDictionary> dictionaryOptional;
        ReadOnlyDictionary initialDictionary;
        Optional<ReadOnlyDefinitionBook> definitionBookOptional;
        ReadOnlyDefinitionBook initialDataDefinition;

        try {
            addressBookOptional = storage.readContactsList();
            if (!addressBookOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample AddressBook");
            }
            initialDataAddress = addressBookOptional.orElseGet(SampleDataUtil::getSampleContactsList);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty AddressBook");
            initialDataAddress = new ContactsList();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty AddressBook");
            initialDataAddress = new ContactsList();
        }

        try {
            noteBookOptional = storage.readNoteBook();
            if (!noteBookOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample NoteBook");
            }
            initialDataNote = noteBookOptional.orElseGet(SampleDataUtil::getSampleNoteBook);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty AddressBook");
            initialDataNote = new NoteBook();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty AddressBook");
            initialDataNote = new NoteBook();
        }

        try {
            dictionaryOptional = storage.readDictionary();
            if (!dictionaryOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample Dictionary");
            }
            initialDictionary = dictionaryOptional.orElseGet(SampleDataUtil::getSampleDictionary);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty Dictionary");
            initialDictionary = new Dictionary();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty Dictionary");
            initialDictionary = new Dictionary();
        }

        try {
            definitionBookOptional = storage.readDefinitionBook();
            if (!definitionBookOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample DefinitionBook");
            }
            initialDataDefinition = definitionBookOptional.orElseGet(SampleDataUtil::getSampleDefinitionBook);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty DefinitionBook");
            initialDataDefinition = new DefinitionBook();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty DefinitionBook");
            initialDataDefinition = new DefinitionBook();
        }

        return new ModelManager(initialDataAddress, userPrefs, initialDataNote,
                initialDictionary, initialDataDefinition);
    }

    private void initLogging(Config config) {
        LogsCenter.init(config);
    }

    /**
     * Returns a {@code Config} using the file at {@code configFilePath}. <br>
     * The default file path {@code Config#DEFAULT_CONFIG_FILE} will be used instead
     * if {@code configFilePath} is null.
     */
    protected Config initConfig(Path configFilePath) {
        Config initializedConfig;
        Path configFilePathUsed;

        configFilePathUsed = Config.DEFAULT_CONFIG_FILE;

        if (configFilePath != null) {
            logger.info("Custom Config file specified " + configFilePath);
            configFilePathUsed = configFilePath;
        }

        logger.info("Using config file : " + configFilePathUsed);

        try {
            Optional<Config> configOptional = ConfigUtil.readConfig(configFilePathUsed);
            initializedConfig = configOptional.orElse(new Config());
        } catch (DataConversionException e) {
            logger.warning("Config file at " + configFilePathUsed + " is not in the correct format. "
                    + "Using default config properties");
            initializedConfig = new Config();
        }

        //Update config file in case it was missing to begin with or there are new/unused fields
        try {
            ConfigUtil.saveConfig(initializedConfig, configFilePathUsed);
        } catch (IOException e) {
            logger.warning("Failed to save config file : " + StringUtil.getDetails(e));
        }
        return initializedConfig;
    }

    /**
     * Returns a {@code UserPrefs} using the file at {@code storage}'s user prefs file path,
     * or a new {@code UserPrefs} with default configuration if errors occur when
     * reading from the file.
     */
    protected UserPrefs initPrefs(UserPrefsStorage storage) {
        Path prefsFilePath = storage.getUserPrefsFilePath();
        logger.info("Using prefs file : " + prefsFilePath);

        UserPrefs initializedPrefs;
        try {
            Optional<UserPrefs> prefsOptional = storage.readUserPrefs();
            initializedPrefs = prefsOptional.orElse(new UserPrefs());
        } catch (DataConversionException e) {
            logger.warning("UserPrefs file at " + prefsFilePath + " is not in the correct format. "
                    + "Using default user prefs");
            initializedPrefs = new UserPrefs();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty AddressBook");
            initializedPrefs = new UserPrefs();
        }

        //Update prefs file in case it was missing to begin with or there are new/unused fields
        try {
            storage.saveUserPrefs(initializedPrefs);
        } catch (IOException e) {
            logger.warning("Failed to save config file : " + StringUtil.getDetails(e));
        }

        return initializedPrefs;
    }

    @Override
    public void start(Stage primaryStage) {
        logger.info("Starting AddressBook " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping Address Book ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
