package pl.javastart.restoffers.offer;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/offers")
@RestController
public class OfferRestController {

    private final OfferService offerService;

    public OfferRestController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping("")
    List<OfferDto> getAllOffers(@RequestParam(required = false) String title) {
        if (title == null) {
            return offerService.getAllOffers();
        } else {
            return offerService.getAllOffersContainsTitle(title);
        }
    }

    @GetMapping("/count")
    long countOffers() {
        return offerService.countOffers();
    }

    @PostMapping("")
    ResponseEntity<OfferDto> addOffer(@RequestBody OfferDto dto) {
        return ResponseEntity.ok(offerService.addOffer(dto));
    }

    @GetMapping("/{id}")
    ResponseEntity<OfferDto> findOfferById(@PathVariable Long id) {
        return offerService.findOfferById(id);
    }

    @DeleteMapping("/{id}")
    void deleteOfferById(@PathVariable Long id) {
        offerService.deleteOfferById(id);
    }

}
