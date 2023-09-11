package com.kadama.solution.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.kadama.solution.model.Campaign;
import com.kadama.solution.model.Liste;
import com.kadama.solution.service.CampaignService;

import java.util.List;

@RestController
@RequestMapping("/api/campaigns")
public class CampaignController {

    @Autowired
    private CampaignService campaignService;

    @GetMapping("/{id}")
    public ResponseEntity<Campaign> getCompaignById(@PathVariable int id) {
        Campaign compaign = campaignService.getCompaignById(id);
        if (compaign != null) {
            return ResponseEntity.ok(compaign);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Campaign>> getAllCompaigns() {
        List<Campaign> compaigns = campaignService.getAllCompaigns();
        return ResponseEntity.ok(compaigns);
    }

    @PostMapping("/create")
    public ResponseEntity<Campaign> createCampaign(@RequestBody Campaign campaign) {
        Campaign createdCampaign = campaignService.createCampaign(campaign);
        if (createdCampaign != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdCampaign);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Campaign> updateCampaign(@PathVariable int id, @RequestBody Campaign updatedCampaign) {
        Campaign existingCampaign = campaignService.getCompaignById(id);
        if (existingCampaign != null) {
            // Assurez-vous que la campagne existante est associée à l'utilisateur actuel si nécessaire
            // Effectuez ici la logique de mise à jour en utilisant les données de "updatedCampaign"
            Campaign updated = campaignService.updateCampaign(id, updatedCampaign);
            if (updated != null) {
                return ResponseEntity.ok(updated);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/by-utilisateur/{utilisateurId}")
    public ResponseEntity<List<Campaign>> getCampaignsByUtilisateurId(@PathVariable Long utilisateurId) {
        List<Campaign> campaigns = campaignService.getCampaignsByUserId(utilisateurId);
        if (campaigns != null && !campaigns.isEmpty()) {
            return ResponseEntity.ok(campaigns);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCampaign(@PathVariable int id) {
       
            return ResponseEntity.noContent().build();
     
    }

    
    @GetMapping("/{id}/listes")
    public ResponseEntity<List<Liste>> getListesByCampaignId(@PathVariable int id) {
        Campaign campaign = campaignService.getCompaignById(id);
        if (campaign != null) {
            List<Liste> listes = campaign.getListes();
            return ResponseEntity.ok(listes);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Add endpoints for creating, updating, and deleting Compaigns
}
