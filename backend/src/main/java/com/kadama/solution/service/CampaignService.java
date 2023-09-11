package com.kadama.solution.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kadama.solution.model.Campaign;
import com.kadama.solution.repository.CampaignRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CampaignService {

    @Autowired
    private CampaignRepository campaignRepository;

    public List<Campaign> getAllCompaigns() {
        return campaignRepository.findAll();
    }
    public Campaign getCompaignById(int id) {
        Optional<Campaign> optionalCompaign = campaignRepository.findById(id);
        return optionalCompaign.orElse(null);
    }
    
    public Campaign createCampaign(Campaign campaign) {
        // Implement your logic for creating a new Campaign resource here
        // You can use the CampaignRepository to save the new resource
        // For example, you can save the new Campaign instance like this:
        return campaignRepository.save(campaign);
    }

    
    public Campaign updateCampaign(int id, Campaign updatedCampaign) {
        // Recherchez la campagne existante par ID
        Optional<Campaign> optionalCampaign = campaignRepository.findById(id);
        if (optionalCampaign.isPresent()) {
            Campaign existingCampaign = optionalCampaign.get();
            // Effectuez ici la logique de mise à jour en utilisant les données de "updatedCampaign"
            // Par exemple, vous pouvez mettre à jour le nom de la campagne
            existingCampaign.setNom(updatedCampaign.getNom());
            // Ajoutez d'autres mises à jour au besoin
            return campaignRepository.save(existingCampaign);
        }
        return null; // Retourne null si la campagne n'est pas trouvée
    }

    public void deleteCampaign(int id) {
        // Supprimez la campagne par ID
        campaignRepository.deleteById(id);
    }

    public List<Campaign> getCampaignsByUserId(Long userId) {
        // Récupérez toutes les campagnes associées à un utilisateur spécifique par son ID
        return campaignRepository.findByUtilisateurId(userId);
    }
    
    

    // Ajoutez ici des méthodes de service pour la gestion des campagnes
}
