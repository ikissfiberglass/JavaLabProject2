package Service;

import Model.Pracownik;
import Model.PracownikRepository;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class pracownikService implements Runnable{

    private static final PracownikRepository pracownikRepository = new PracownikRepository();

//    private final ExecutorService pool = Executors.newFixedThreadPool(10);

//    private genericArray<Void> futures = new genericArray<>(pracownikRepository.getPracownicySize());
    private static genericArray<CompletableFuture<Void>> futures = new genericArray<>(pracownikRepository.getPracownicySize());



    public static void serializeToSer(String fileName) throws IOException {

        final ExecutorService pool = Executors.newFixedThreadPool(10);

//        for( int i = 0; i < futures.getSize(); i++ ) {
//            futures.doRunAsync();
//            futures.getByIndex(i).runAsync;
//            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {});
//        }

        for(int i = 0; i < pracownikRepository.getPracownicySize(); i++){
            CompletableFuture<Void> future = CompletableFuture.runAsync( , pool);

        }

    }

    public void saveToFile(String fileName) throws IOException {
        final ExecutorService pool = Executors.newFixedThreadPool(10);
        for(int i = 0; i < pracownikRepository.getPracownicySize(); i++){
            CompletableFuture<Void> future = CompletableFuture.runAsync();

        }

    }


    @Override
    public void run() {

    }
}
