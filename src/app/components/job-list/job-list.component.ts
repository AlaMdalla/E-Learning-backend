import { Component, OnInit } from '@angular/core';
import { JobService } from '../../services/job.service';
import { Job } from '../../models/Job';

@Component({
  selector: 'app-job-list',
  templateUrl: './job-list.component.html',
  styleUrls: ['./job-list.component.css'],
})
export class JobListComponent implements OnInit {
  jobs: Job[] = [];
  filteredJobs: Job[] = [];
  searchQuery: string = '';
  sortColumn: keyof Job | null = null;
  sortDirection: 'asc' | 'desc' = 'asc';

  constructor(private jobService: JobService) {}

  ngOnInit(): void {
    this.loadJobs();
  }

  loadJobs(): void {
    this.jobService.getJobs().subscribe({
      next: (data) => {
        this.jobs = data;
        this.jobs.forEach(job => {
          if (job.image && job.image.endsWith('...')) {
            this.jobService.getJobImage(job.jobId).subscribe({
              next: (fullImage) => job.image = fullImage,
              error: (err) => console.error('Image fetch error:', err),
            });
          }
        });
        this.applyFilterAndSort();
      },
      error: (err) => console.error('Jobs fetch error:', err),
    });
  }

  deleteJob(id: number): void {
    if (confirm('Are you sure?')) {
      this.jobService.deleteJob(id).subscribe({
        next: () => this.loadJobs(),
        error: (err) => console.error('Delete error:', err),
      });
    }
  }

  getGoogleMapsUrl(location: string): string {
    return `https://www.google.com/maps/search/?api=1&query=${encodeURIComponent(location)}`;
  }

  onSearch(): void {
    this.applyFilterAndSort();
  }

  sort(column: keyof Job): void {
    if (this.sortColumn === column) {
      this.sortDirection = this.sortDirection === 'asc' ? 'desc' : 'asc';
    } else {
      this.sortColumn = column;
      this.sortDirection = 'asc';
    }
    this.applyFilterAndSort();
  }

  private applyFilterAndSort(): void {
    let result = [...this.jobs];

    // Filter
    if (this.searchQuery.trim()) {
      const query = this.searchQuery.toLowerCase();
      result = result.filter(job =>
        (job.title?.toLowerCase().includes(query) || '') ||
        (job.department?.toLowerCase().includes(query) || '') ||
        (job.location?.toLowerCase().includes(query) || '') ||
        (job.status?.toLowerCase().includes(query) || '')
      );
    }

    // Sort
    if (this.sortColumn !== null) {
      const column = this.sortColumn as keyof Job; // Type assertion after null check
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
        } else if (aValue instanceof Date && bValue instanceof Date) {
          return this.sortDirection === 'asc'
            ? aValue.getTime() - bValue.getTime()
            : bValue.getTime() - aValue.getTime();
        }
        return 0; // Default case (e.g., null or mismatched types)
      });
    }

    this.filteredJobs = result;
  }
}