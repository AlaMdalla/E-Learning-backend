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
  filteredCandidates: Candidate[] = [];
  searchQuery: string = '';
  sortColumn: keyof Candidate | null = null;
  sortDirection: 'asc' | 'desc' = 'asc';
  private baseUrl = 'http://localhost:8081';

  constructor(private candidateService: CandidateService) {}

  ngOnInit(): void {
    this.loadCandidates();
  }

  loadCandidates(): void {
    this.candidateService.getCandidates().subscribe({
      next: (data) => {
        this.candidates = data;
        this.candidates.forEach(candidate => {
          if (candidate.resumeUrl && candidate.resumeUrl.endsWith('...')) {
            this.candidateService.getCandidateResume(candidate.id).subscribe({
              next: (fullResume) => candidate.resumeUrl = fullResume,
              error: (err) => console.error('Resume fetch error:', err),
            });
          }
        });
        this.applyFilterAndSort();
      },
      error: (err) => console.error('Error fetching candidates:', err),
    });
  }

  deleteCandidate(id: number): void {
    if (confirm('Are you sure?')) {
      this.candidateService.deleteCandidate(id).subscribe({
        next: () => this.loadCandidates(),
        error: (err) => console.error('Delete error:', err),
      });
    }
  }

  getResumeUrl(resumeUrl: string): string {
    return `${this.baseUrl}${resumeUrl}`;
  }

  onSearch(): void {
    this.applyFilterAndSort();
  }

  sort(column: keyof Candidate): void {
    if (this.sortColumn === column) {
      this.sortDirection = this.sortDirection === 'asc' ? 'desc' : 'asc';
    } else {
      this.sortColumn = column;
      this.sortDirection = 'asc';
    }
    this.applyFilterAndSort();
  }

  private applyFilterAndSort(): void {
    let result = [...this.candidates];

    // Filter
    if (this.searchQuery.trim()) {
      const query = this.searchQuery.toLowerCase();
      result = result.filter(candidate =>
        (candidate.email?.toLowerCase().includes(query) || '') ||
        (candidate.phone?.toLowerCase().includes(query) || '') ||
        (candidate.status?.toLowerCase().includes(query) || '') ||
        (candidate.jobTitle?.toLowerCase().includes(query) || '')
      );
    }

    // Sort
    if (this.sortColumn !== null) {
      const column = this.sortColumn as keyof Candidate; // Type assertion after null check
      result.sort((a, b) => {
        const aValue = a[column];
        const bValue = b[column];
        
        // Handle different types
        if (typeof aValue === 'string' && typeof bValue === 'string') {
          return this.sortDirection === 'asc'
            ? aValue.localeCompare(bValue)
            : bValue.localeCompare(aValue);
        } else if (typeof aValue === 'number' && typeof bValue === 'number') {
          return this.sortDirection === 'asc'
            ? aValue - bValue
            : bValue - aValue;
        }
        return 0; // Default case (e.g., null or mismatched types)
      });
    }

    this.filteredCandidates = result;
  }
}