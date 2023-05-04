package pl.javastart.restoffers.offer;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.javastart.restoffers.category.CategoryRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OfferService {

    private final OfferRepository offerRepository;

    public OfferService(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    public List<OfferDto> getAllOffers() {
        return offerRepository.findAll().stream().map(this::offerToDto).collect(Collectors.toList());
    }

    public long countOffers() {
        return offerRepository.count();
    }

    public ResponseEntity<OfferDto> findOfferById(Long id) {
        Optional<Offer> optionalOffer = offerRepository.findById(id);
        return optionalOffer.map(this::offerToDto).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public OfferDto addOffer(OfferDto offerDto) {
        Offer offer = new Offer(offerDto.getTitle(), offerDto.getDescription(), offerDto.getImgUrl(), offerDto.getPrice(), offerDto.getCategory());
        offerRepository.save(offer);
        return offerToDto(offer);
    }

    public List<OfferDto> getAllOffersContainsTitle(String title) {
        return offerRepository.findAllByTitleContainsIgnoreCase(title).stream().map(this::offerToDto).collect(Collectors.toList());
    }

    public void deleteOfferById(Long id) {
        try {
            offerRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ignore) {
            // ignore
        }
    }

    private OfferDto offerToDto(Offer offer) {
        return new OfferDto(offer.getId(), offer.getTitle(), offer.getDescription(), offer.getImgUrl(), offer.getPrice(), offer.getCategory());
    }
}
