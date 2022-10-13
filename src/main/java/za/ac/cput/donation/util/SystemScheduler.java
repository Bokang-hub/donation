package za.ac.cput.donation.util;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import za.ac.cput.donation.entity.Parcel;
import za.ac.cput.donation.entity.ParcelRequest;
import za.ac.cput.donation.entity.Record;
import za.ac.cput.donation.factory.RecordFactory;

import java.time.LocalDate;

@EnableScheduling
@Service
public class SystemScheduler {
    private final ParcelRequestServiceImpl parcelRequestService;
    private final RecordServiceImpl recordService;
    private final ParcelServiceImpl parcelService;

    public SystemScheduler(ParcelRequestServiceImpl parcelRequestService, RecordServiceImpl recordService, ParcelServiceImpl parcelService){
        this.parcelRequestService = parcelRequestService;
        this.recordService = recordService;
        this.parcelService = parcelService;
    }

    @Scheduled(cron = "30 * * * * *")
    public void distributeAvailableParcels(){
        System.err.println("STARTED DISTRIBUTING AVAILABLE PARCELS");
        for(int p = 0; p < parcelService.findAllAvailable().size() && parcelRequestService.findAllPending().size() > 0; p++){
            Parcel tp = parcelService.findAllAvailable().get(p);
            ParcelRequest pr = parcelRequestService.findAllPending()
                    .stream()
                    .filter(f -> f.getDonationType().contains(tp.getType()))
                    .findFirst().orElse(null);
            if(pr != null){
                ParcelRequest n = new ParcelRequest.Builder().copy(pr).setIsReceived(true).build();
                parcelRequestService.save(n);
                Parcel v = new Parcel.Builder().copy(tp).setStatus(false).build();
                parcelService.save(v);

                Record r = RecordFactory.createRecord(n.getId(), LocalDate.now().toString(), v.getType(), true);
                recordService.save(r);
            }
        }
        System.err.println("FINISHED DISTRIBUTING PARCELS");
    }

    /*
    public void distributeAvailableParcels(){
        System.err.println("STARTED DISTRIBUTING AVAILABLE PARCELS");
        for(int p = 0; p < parcelService.findAllAvailable().size(); p++){
            if(parcelRequestService.findAllPending().size() > 0){
                Parcel tp = parcelService.findAllAvailable().get(p);
                ParcelRequest pr = parcelRequestService.findAllPending().stream()
                        .filter(f -> f.getDonationType().equalsIgnoreCase(tp.getType()))
                        .findFirst().orElse(null);
                if(pr != null){
                    ParcelRequest n = new ParcelRequest.Builder().copy(pr).setIsReceived(true).build();
                    parcelRequestService.save(n);
                    Parcel v = new Parcel.Builder().copy(tp).setStatus(false).build();
                    parcelService.save(v);

                    Record r = RecordFactory.createRecord(n.getId(), LocalDate.now().toString(), v.getType(), true);
                    recordService.save(r);
                }
            } else {
                return;
            }
        }
        System.err.println("FINISHED DISTRIBUTING PARCELS");
    }
     */
}
