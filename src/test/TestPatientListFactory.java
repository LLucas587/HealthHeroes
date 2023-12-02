package test;

import data_access.CSVDatabaseAccessInterface;
import data_access.CSVDatabaseAccessObject;
import interface_adapter.patientList.PatientListController;
import interface_adapter.patientList.PatientListPresenter;
import use_case.patientList.*;
import view.PatientListView;

import java.io.IOException;
import java.util.ArrayList;

public class TestPatientListFactory {
    public static void main(String[] args) {
        try {
            CSVDatabaseAccessObject databaseAccessObject = new CSVDatabaseAccessObject("data/Doctor1.csv");
            PatientListView view = new PatientListView();
            view.addLeftPanel();
            ArrayList<PatientListOutputData> testArray = new ArrayList<>();
            view.display(testArray);

            FetchPatientsUseCase fetch = new FetchPatientsUseCase(databaseAccessObject);
            AddPatientUseCase add = new AddPatientUseCase(databaseAccessObject);
            DeletePatientUseCase delete = new DeletePatientUseCase(databaseAccessObject);

            PatientListInteractor interactor = new PatientListInteractor(fetch, add, delete);

            PatientListPresenter presenter = new PatientListPresenter(view);
            PatientListController controller = new PatientListController(interactor, presenter);
            view.setController(controller);
            controller.loadPatients();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
