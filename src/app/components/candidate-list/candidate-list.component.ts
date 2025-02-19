import { Component, OnInit } from '@angular/core';
import { CandidateService } from '../../services/candidate.service';
import { Candidate } from '../../models/Candidate';

@Component({
  selector: 'app-candidate-list',
  templateUrl: './candidate-list.component.html',
  styleUrls: ['./candidate-list.component.css'],
})
export class CandidateListComponent implements OnInit {
  candidates: Candidate[] = [];

  constructor(private candidateService: CandidateService) {}
  ngOnInit(): void {
    this.candidateService.getCandidates().subscribe({
      next: (data) => {
        this.candidates = data;
      },
      error: (err) => console.error('Error fetching candidates:', err)
    });
  }
  
  
  
  
  
  

  loadCandidates(): void {
    this.candidateService.getCandidates().subscribe((data) => {
      this.candidates = data;
    });
  }

  deleteCandidate(id: number | undefined): void {
    if (id === undefined || id === null || id <= 0) {
      return;
    }
  
  
    this.candidateService.deleteCandidate(id).subscribe({
      next: () => {
        confirm('Are you sure you want to delete this candidate?')
        this.candidates = this.candidates.filter(c => c.id !== id);
      },
    });
  }
  
  
  
  
  
  
}
