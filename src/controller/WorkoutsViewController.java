package controller;

import dao.*;
import entities.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import util.Helper;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class WorkoutsViewController implements Initializable {

    // Root pane
    @FXML private ListView<Workout> workoutList;
    @FXML private Label timeLabel;
    @FXML private Label durationLabel;
    @FXML private TextArea descriptionText;
    @FXML private Button createTemplateButton;
    @FXML private Button createNewWorkoutButton;
    @FXML private ListView<WorkoutExercise> workoutExercisesList;

    // New workout pane
    @FXML private AnchorPane newWorkoutPane;
    @FXML private Button addNewWorkoutExercise;
    @FXML private Button saveWorkoutButton;
    @FXML private Button cancelWorkoutButton;
    @FXML private DatePicker workoutDatePicker;
    @FXML private TextField workoutHours;
    @FXML private TextField workoutMinutes;
    @FXML private TextField workoutSeconds;
    @FXML private TextArea newWorkoutDescription;
    @FXML private TextField newWorkoutTime;
    @FXML private ToggleButton uteInneToggle;

    @FXML private Label tempSpectatorsLabel;
    @FXML private TextField tempSpectatorsField;
    @FXML private ComboBox<String> weatherAirField;
    @FXML private ComboBox<WorkoutExercise> workoutExercisesForNewWorkoutList;

    // New workout exercise pane
    @FXML private AnchorPane newWorkoutExercisePane;
    @FXML private Button saveWorkoutExerciseButton;
    @FXML private Button cancelWorkoutExerciseButton;
    @FXML private ComboBox<Exercise> allExercisesList;
    @FXML private ToggleButton styrkeUtholdenhetToggle;

    @FXML private TextField weightField;
    @FXML private TextField repsField;
    @FXML private TextField setsField;
    @FXML private TextField distanceField;
    @FXML private TextField weHoursField;
    @FXML private TextField weMinutesField;
    @FXML private TextField weSecondsField;

    @FXML private Label weightTitleLabel;
    @FXML private Label repsTitleLabel;
    @FXML private Label setsTitleLabel;
    @FXML private Label distanceTitleLabel;
    @FXML private Label durationTitleLabel;

    @FXML private ComboBox<Integer> performanceList;
    @FXML private ComboBox<Integer> formList;

    private ArrayList<WorkoutExercise> tempWorkoutExercises = new ArrayList<>();

    private ObservableList<String> weatherList = FXCollections.observableArrayList("Sol", "Overskyet", "Regn");
    private ObservableList<String> airQualityList = FXCollections.observableArrayList("Bra", "Middels", "Dårlig");

    private Boolean inne = false;
    private Boolean styrke = true;
    private Workout selectedWorkout;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hideAllControls();
        loadAllWorkoutsToList();

        newWorkoutPane.setVisible(false);

        workoutList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Workout selection = workoutList.getSelectionModel().getSelectedItem();

                if(selection != null) {
                    selectWorkout(selection);
                }
            }
        });

        createNewWorkoutButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                newWorkoutPane.setVisible(true);
                newWorkoutExercisePane.setVisible(false);

                LocalDate now = LocalDate.now();
                workoutDatePicker.setPromptText(String.valueOf(now.getDayOfMonth()) + "." + String.valueOf(now.getMonthValue() + "." + String.valueOf(now.getYear())));

                weatherAirField.setItems(weatherList);
            }
        });

        addNewWorkoutExercise.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                newWorkoutExercisePane.setVisible(true);

                ObservableList<Integer> oneToTen = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
                formList.setItems(oneToTen);
                performanceList.setItems(oneToTen);

                allExercisesList.setItems(Helper.getAllExercises());
            }
        });

        uteInneToggle.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                inne = uteInneToggle.isSelected();

                uteInneToggle.setText(inne ? "Innendørs" : "Utendørs");
                tempSpectatorsLabel.setText(inne ? "Tilskuere" : "℃");
                weatherAirField.setItems(inne ? airQualityList : weatherList);
                weatherAirField.setPromptText(inne ? "Luftkvalitet" : "Vær");
                tempSpectatorsField.setText("");
            }
        });

        cancelWorkoutButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                clearAllNewWorkoutFields();

                newWorkoutPane.setVisible(false);
            }
        });

        saveWorkoutButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (workoutHours.getText().equals("")) {
                    workoutHours.setText("0");
                }

                if (workoutMinutes.getText().equals("")) {
                    workoutMinutes.setText("0");
                }

                if (workoutSeconds.getText().equals("")) {
                    workoutSeconds.setText("0");
                }

                Integer hours;
                Integer minutes;
                Integer seconds;
                try {
                    hours = Integer.parseInt(workoutHours.getText());
                    minutes = Integer.parseInt(workoutMinutes.getText());
                    seconds = Integer.parseInt(workoutSeconds.getText());
                } catch (Exception e) {
                    System.out.println("Lengde på økt må være gyldig.");
                    return;
                }

                if(hours < 0 || minutes < 0 || seconds < 0 || minutes > 59 || seconds > 59) {
                    System.out.println("Ikke gyldig lengde på økta.");
                    return;
                }

                String[] timeComponents = new String[2];
                Integer clockHours;
                Integer clockMinutes;
                LocalTime time;
                try {
                    timeComponents = newWorkoutTime.getText().split(":");
                    clockHours = Integer.parseInt(timeComponents[0]);
                    clockMinutes = Integer.parseInt(timeComponents[1]);
                    time = LocalTime.of(clockHours, clockMinutes);
                } catch (Exception e) {
                    System.out.println("Feil format på klokkeslett (TT:MM)");
                    return;
                }

                LocalDate date = workoutDatePicker.getValue() != null ? workoutDatePicker.getValue() : LocalDate.now();

                Float temperature = (float)-273.15;
                String weather = null;
                String airQuality = null;
                Integer spectators = -100;

                if (!tempSpectatorsField.getText().equals("")) {
                    try {
                        if(inne) {
                            spectators = Integer.parseInt(tempSpectatorsField.getText());
                        } else {
                            temperature = Float.parseFloat(tempSpectatorsField.getText());
                        }
                    } catch (Exception e) {
                        System.out.println("Ikke gyldig tall på tilskuere/temp.");
                        return;
                    }
                }

                if (inne) {
                    IndoorWorkout iw = new IndoorWorkout();
                    iw.setAirQuality(weatherAirField.getSelectionModel().getSelectedIndex());
                    iw.setSpectators(spectators);
                    iw.setDate(date);
                    iw.setLength((hours * 3600) + (minutes * 60) + seconds);
                    iw.setNote(descriptionText.getText());

                    IndoorWorkoutDao.insert(iw);
                }
                else {
                    weather = (String)weatherAirField.getValue();

                    OutdoorWorkout ow = new OutdoorWorkout();
                    ow.setLength((hours * 3600) + (minutes * 60) + seconds);
                    ow.setDate(date);
                    ow.setNote(descriptionText.getText());
                    ow.setTemperature(temperature);
                    ow.setWeather(weatherAirField.getSelectionModel().getSelectedItem());

                    OutdoorWorkoutDao.insert(ow);
                }

                loadAllWorkoutsToList();
                clearAllNewWorkoutFields();

                newWorkoutPane.setVisible(false);
            }
        });

        cancelWorkoutExerciseButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                newWorkoutExercisePane.setVisible(false);
            }
        });

        saveWorkoutExerciseButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (allExercisesList.getValue() != null) {
                    if(styrke) {
                        Integer weight = null;
                        Integer reps = null;
                        Integer sets = null;

                        try {
                            weight = Integer.parseInt(weightField.getText());
                            reps = Integer.parseInt(repsField.getText());
                            sets = Integer.parseInt(setsField.getText());

                        } catch (Exception e) {
                            System.out.println("Ikke gyldig vekt, reps eller sett.");
                            return;
                        }

                        StrengthExercise e = new StrengthExercise();
                        e.setWeight(weight);
                        e.setRepetitions(reps);
                        e.setSets(sets);
                        e.setForm(formList.getSelectionModel().getSelectedItem());
                        e.setPerformance(performanceList.getSelectionModel().getSelectedItem());
                        tempWorkoutExercises.add(e);
                    } else {
                        Integer distance = null;
                        Integer duration = null;

                        try {
                            distance = Integer.parseInt(distanceField.getText());
                            Integer hours = Integer.parseInt(weHoursField.getText());
                            Integer minutes = Integer.parseInt(weMinutesField.getText());
                            Integer seconds = Integer.parseInt(weSecondsField.getText());
                            duration = (hours * 3600) + (minutes * 60) + (seconds);
                        } catch (Exception e) {
                            System.out.println("Ugyldigheter");
                        }

                        CardioExercise e = new CardioExercise();
                        e.setDistance(distance);
                        e.setTime(duration);
                        e.setForm(formList.getSelectionModel().getSelectedItem());
                        e.setPerformance(performanceList.getSelectionModel().getSelectedItem());
                        tempWorkoutExercises.add(e);
                    }

                    reloadWorkoutExercisesForNewWorkout();
                    newWorkoutExercisePane.setVisible(false);

                } else {
                    System.out.println("Velg en øvelse.");
                    return;
                }
            }
        });

        styrkeUtholdenhetToggle.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                styrke = !styrkeUtholdenhetToggle.isSelected();
                if(styrke) { styrkeUtholdenhetToggle.setText("Styrke"); }
                else { styrkeUtholdenhetToggle.setText("Utholdenhet"); }

                weightField.setVisible(styrke);
                repsField.setVisible(styrke);
                setsField.setVisible(styrke);
                distanceField.setVisible(!styrke);
                weHoursField.setVisible(!styrke);
                weMinutesField.setVisible(!styrke);
                weSecondsField.setVisible(!styrke);
                weightTitleLabel.setVisible(styrke);
                repsTitleLabel.setVisible(styrke);
                setsTitleLabel.setVisible(styrke);
                distanceTitleLabel.setVisible(!styrke);
                durationTitleLabel.setVisible(!styrke);
            }
        });
    }

    private void reloadWorkoutExercisesForNewWorkout() {
        ObservableList<WorkoutExercise> list = FXCollections.observableArrayList();
        for (WorkoutExercise workoutExercise : tempWorkoutExercises) {
            list.add(workoutExercise);
        }

        workoutExercisesForNewWorkoutList.setItems(list);
    }

    private void clearAllNewWorkoutFields() {
        workoutDatePicker.setValue(null);
        newWorkoutTime.setText("");
        workoutHours.setText("");
        workoutMinutes.setText("");
        workoutSeconds.setText("");
        newWorkoutDescription.setText("");
    }

    private void hideAllControls() {
        timeLabel.setVisible(false);
        durationLabel.setVisible(false);
        descriptionText.setVisible(false);
        createTemplateButton.setVisible(false);
        workoutExercisesList.setVisible(false);
    }

    private void showAllControls() {
        timeLabel.setVisible(true);
        durationLabel.setVisible(true);
        descriptionText.setVisible(true);
        createTemplateButton.setVisible(true);
        workoutExercisesList.setVisible(true);
    }

    private void loadAllWorkoutsToList() {
        ObservableList<Workout> list = FXCollections.observableArrayList();
        ArrayList<Workout> all = new ArrayList<>();
        all.addAll(IndoorWorkoutDao.selectAll());
        all.addAll(OutdoorWorkoutDao.selectAll());

        for (Workout workout : all) {
            list.add(workout);
        }

        workoutList.setItems(list);
    }

    private void selectWorkout(Workout workout) {
        selectedWorkout = workout;

        timeLabel.setText(selectedWorkout.getName());
        descriptionText.setText(selectedWorkout.getNote());
        durationLabel.setText(String.valueOf(selectedWorkout.getLength()));

        showAllControls();

        ObservableList<WorkoutExercise> list = FXCollections.observableArrayList();
        workoutExercisesList.setItems(list);
    }
}
