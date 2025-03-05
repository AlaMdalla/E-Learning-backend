import { Component, OnInit } from '@angular/core';
import { CandidateService } from '../../services/candidate.service';
import { JobService } from '../../services/job.service';
import { Candidate } from '../../models/Candidate';
import { Job } from '../../models/Job';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-candidate-form',
  templateUrl: './candidate-form.component.html',
  styleUrls: ['./candidate-form.component.css'],
})
export class CandidateFormComponent implements OnInit {
  candidate: Candidate = new Candidate();
  jobs: Job[] = [];
  isEditMode: boolean = false;

  constructor(
    private candidateService: CandidateService,
    private jobService: JobService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadJobs();
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.isEditMode = true;
      this.candidateService.getCandidateById(+id).subscribe({
        next: (data) => {
          this.candidate = data;
          if (this.candidate.applicationDate) {
            this.candidate.applicationDate = new Date(this.candidate.applicationDate).toISOString().slice(0, 16);
          }
        },
        error: (err) => console.error('❌ Error fetching candidate:', err),
      });
    }
  }

  loadJobs(): void {
    this.jobService.getJobs().subscribe({
      next: (data) => {
        this.jobs = data;
        if (this.jobs.length > 0 && !this.candidate.jobId) {
          this.candidate.jobId = this.jobs[0].jobId;
        }
      },
      error: (err) => console.error('❌ Error fetching jobs:', err),
    });
  }

  onFileSelected(event: Event): void {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files[0]) {
      const file = input.files[0];
      if (file.size > 5 * 1024 * 1024) {
        alert('Resume too large! Max 5MB.');
        return;
      }
      this.candidateService.uploadResume(file).subscribe({
        next: (url) => {
          this.candidate.resumeUrl = url; // Set the returned URL
          console.log('Resume uploaded, URL:', url);
        },
        error: (err) => console.error('Error uploading resume:', err),
      });
    }
  }

  onSubmit(): void {
    if (!this.candidate.jobId) {
      alert('Please select a job.');
      return;
    }
    if (!this.candidate.resumeUrl) {
      alert('Please upload a resume.');
      return;
    }
    const candidateData = {
      id: this.isEditMode ? this.candidate.id : undefined,
      email: this.candidate.email,
      phone: this.candidate.phone,
      resumeUrl: this.candidate.resumeUrl,
      applicationDate: this.candidate.applicationDate ? new Date(this.candidate.applicationDate).toISOString() : null,
      status: this.candidate.status,
      jobId: this.candidate.jobId
    };
    console.log('Payload sent:', JSON.stringify(candidateData, null, 2));
    if (this.isEditMode && this.candidate.id) {
      console.log('✏️ Updating candidate with ID:', this.candidate.id);
      this.candidateService.updateCandidate(this.candidate.id, candidateData).subscribe({
        next: () => this.router.navigate(['/candidates']),
        error: (err) => console.error('Update error:', err),
      });
    } else {
      console.log('➕ Creating a new candidate');
      this.candidateService.createCandidate(candidateData).subscribe({
        next: () => this.router.navigate(['/candidates']),
        error: (err) => console.error('Create error:', err),
      });
    }
  }
}