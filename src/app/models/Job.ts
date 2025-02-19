export class Job {
  jobId!: number;
  title!: string;
  department!: string;
  location!: string;
  description!: string;
  requirements!: string;
  postedDate!: Date; // Fixed to Date type
  status!: string;
}
